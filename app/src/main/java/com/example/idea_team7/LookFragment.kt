package com.example.idea_team7

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.idea_team7.databinding.FragmentLookBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class LookFragment : Fragment() {
    private lateinit var binding: FragmentLookBinding
    private var youTubePlayer: YouTubePlayer? = null
    private val handler = Handler(Looper.getMainLooper())
    private var captionsOriginal = ""
    private var captionsFriend = ""
    private var captionsExport = ""
    private var currentCaptions = ""
    private val captionHandlers = mutableListOf<Handler>()
    private var currentCaptionIndex = -1
    private var playbackPosition = 0f
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCaptionsFromJson()
    }

    private fun loadCaptionsFromJson() {
        val inputStream: InputStream = resources.openRawResource(R.raw.captions)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        captionsOriginal = jsonObject.getJSONArray("captionsOriginal").toString()
        captionsFriend = jsonObject.getJSONArray("captionsFriend").toString()
        captionsExport = jsonObject.getJSONArray("captionsExport").toString()
        currentCaptions = captionsOriginal
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showVideo()
        clickListener()
        setupButtonListeners()
        selectButton(binding.originalIv, binding.originalTv) // 기본 상태 설정
    }

    private fun clickListener() {
        binding.backBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment_container, HomeFragment())
                .commitAllowingStateLoss()
        }
        binding.playBtn.setOnClickListener {
            if (!isPlaying) {
                youTubePlayer?.play()
                startCaptions(playbackPosition)
                isPlaying = true
            }
        }
        binding.pauseBtn.setOnClickListener {
            if (isPlaying) {
                youTubePlayer?.pause()
                stopCaptions()
                isPlaying = false
            }
        }
    }

    private fun showVideo() {
        val adsSpf = requireActivity().getSharedPreferences("Address", Context.MODE_PRIVATE)
        val id = adsSpf.getString("id", "")

        val youTubePlayerView: YouTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                this@LookFragment.youTubePlayer = youTubePlayer
                val videoId = id
                youTubePlayer.cueVideo(videoId!!, 0f)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                super.onCurrentSecond(youTubePlayer, second)
                if (isPlaying) {
                    playbackPosition = second
                }
            }
        })
    }

    private fun startCaptions(startPosition: Float) {
        stopCaptions()
        currentCaptionIndex = -1
        val captionsArray = JSONArray(currentCaptions)
        for (i in 0 until captionsArray.length()) {
            val captionObject = captionsArray.getJSONObject(i)
            val text = captionObject.getString("text")
            val start = captionObject.getDouble("start").toFloat()
            val duration = captionObject.getDouble("duration").toFloat()

            if (start >= startPosition) {
                val startHandler = Handler(Looper.getMainLooper())
                startHandler.postDelayed({
                    currentCaptionIndex = i
                    updateCaptions(captionsArray)
                    val endHandler = Handler(Looper.getMainLooper())
                    endHandler.postDelayed({
                        if (currentCaptionIndex == i) {
                            binding.nowCaptionTv.alpha = 0.2f
                            binding.backCaptionTv.alpha = 0.5f
                            binding.nextCaptionTv.alpha = 0.2f
                        }
                    }, (duration * 1000).toLong())
                    captionHandlers.add(endHandler)
                }, ((start - startPosition) * 1000).toLong())
                captionHandlers.add(startHandler)
            }
        }
    }

    private fun updateCaptions(captionsArray: JSONArray) {
        val currentText =
            if (currentCaptionIndex >= 0) captionsArray.getJSONObject(currentCaptionIndex)
                .getString("text") else ""
        val previousText =
            if (currentCaptionIndex > 0) captionsArray.getJSONObject(currentCaptionIndex - 1)
                .getString("text") else ""
        val nextText =
            if (currentCaptionIndex < captionsArray.length() - 1) captionsArray.getJSONObject(
                currentCaptionIndex + 1
            ).getString("text") else ""

        binding.backCaptionTv.text = previousText
        binding.nowCaptionTv.text = currentText
        binding.nextCaptionTv.text = nextText

        binding.backCaptionTv.visibility =
            if (previousText.isEmpty()) View.INVISIBLE else View.VISIBLE
        binding.nowCaptionTv.visibility = if (currentText.isEmpty()) View.GONE else View.VISIBLE
        binding.nextCaptionTv.visibility = if (nextText.isEmpty()) View.GONE else View.VISIBLE

        binding.backCaptionTv.alpha = 0.3f
        binding.nowCaptionTv.alpha = 1.0f
        binding.nextCaptionTv.alpha = 0.3f
    }

    private fun stopCaptions() {
        captionHandlers.forEach { it.removeCallbacksAndMessages(null) }
        captionHandlers.clear()
        binding.backCaptionTv.alpha = 0.2f
        binding.nowCaptionTv.alpha = 0.5f
        binding.nextCaptionTv.alpha = 0.2f
    }

    private fun setupButtonListeners() {
        binding.originalIv.setOnClickListener {
            currentCaptions = captionsOriginal
            selectButton(binding.originalIv, binding.originalTv)
            restartVideoAndCaptions()
        }
        binding.friendIv.setOnClickListener {
            currentCaptions = captionsFriend
            selectButton(binding.friendIv, binding.friendTv)
            restartVideoAndCaptions()
        }
        binding.exportIv.setOnClickListener {
            currentCaptions = captionsExport
            selectButton(binding.exportIv, binding.exportTv)
            restartVideoAndCaptions()
        }
    }

    private fun selectButton(selectedImageView: ImageView, selectedTextView: TextView) {
        val imageViews = listOf(binding.originalIv, binding.friendIv, binding.exportIv)
        val textViews = listOf(binding.originalTv, binding.friendTv, binding.exportTv)

        imageViews.forEachIndexed { index, imageView ->
            if (imageView == selectedImageView) {
                imageView.setBackgroundResource(R.drawable.rectangle_corner_10)
                imageView.backgroundTintList =
                    resources.getColorStateList(R.color.selected_button_background)
                textViews[index].setTextColor(resources.getColor(R.color.white))
            } else {
                imageView.setBackgroundResource(R.drawable.rectangle_corner_10)
                imageView.backgroundTintList =
                    resources.getColorStateList(R.color.unselected_button_background)
                textViews[index].setTextColor(resources.getColor(R.color.unselected_button_text))
            }
        }
    }

    private fun restartVideoAndCaptions() {
        youTubePlayer?.seekTo(0f)
        playbackPosition = 0f
        startCaptions(playbackPosition)
        youTubePlayer?.play()
        isPlaying = true
    }
}
