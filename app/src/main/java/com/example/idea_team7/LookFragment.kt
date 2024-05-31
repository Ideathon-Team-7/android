package com.example.idea_team7

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.idea_team7.databinding.FragmentLookBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.json.JSONArray

class LookFragment : Fragment() {
    private lateinit var binding: FragmentLookBinding
    private var youTubePlayer: YouTubePlayer? = null
    private val handler = Handler(Looper.getMainLooper())
    private val captions = """
        [
            {"text": "I’ve had my ups and downs - ", "start": 3.3, "duration": 2.1},
            {"text": "my fair share of bumpy roads and heavy winds.", "start": 5.8, "duration": 3.733},
            {"text": "That’s what made me what I am today. ", "start": 9.868, "duration": 2.898},
            {"text": "Now I stand here before you.", "start": 13.901, "duration": 2.169},
            {"text": "What you see is a body crafted to perfection,", "start": 16.985, "duration": 3.036},
            {"text": "a pair of legs engineered to defy the laws of physics …", "start": 21.07, "duration": 3.647},
            {"text": "and a mind set to master the most epic of splits.", "start": 25.1, "duration": 5.297},
            {"text": "This test was set up to demonstrate the stability", "start": 61.181, "duration": 1.852},
            {"text": "and precision of Volvo Dynamic Steering.", "start": 63.48, "duration": 4.12},
            {"text": "It was carried out by professionals in a closed-off area", "start": 72.88, "duration": 3.12}
        ]
    """

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    private fun clickListener() {
        binding.backBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment_container, HomeFragment())
                .commitAllowingStateLoss()
        }
        binding.playBtn.setOnClickListener {
            youTubePlayer?.play()
            startCaptions()
        }
    }
    private fun showVideo(){
        val adsSpf = requireActivity().getSharedPreferences("Address", MODE_PRIVATE)
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
        })
    }
    private fun startCaptions() {
        val captionsArray = JSONArray(captions)
        for (i in 0 until captionsArray.length()) {
            val captionObject = captionsArray.getJSONObject(i)
            val text = captionObject.getString("text")
            val start = captionObject.getDouble("start").toFloat()
            val duration = captionObject.getDouble("duration").toFloat()

            handler.postDelayed({
                binding.captionTv.text = text
                binding.captionTv.visibility = View.VISIBLE
                handler.postDelayed({
                    binding.captionTv.visibility = View.GONE
                }, (duration * 1000).toLong())
            }, (start * 1000).toLong())
        }
    }

    fun onClickBackBtn() {
//        findNavController().popBackStack()
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_fragment_container, HomeFragment())
            .commitAllowingStateLoss()
    }
}