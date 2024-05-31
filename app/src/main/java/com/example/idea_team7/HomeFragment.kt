package com.example.idea_team7

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import com.example.idea_team7.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.view = this
        binding.addressEt.addTextChangedListener()
    }

    private fun validAddress() = !binding.addressEt.text.isNullOrEmpty()
    fun onClickInputDone() {
        binding.addressLayout.error = if (!validAddress()) "유투브 주소를 입력해주세요" else null

        var address = binding.addressEt.text.toString().trim()
        val adsSpf = requireActivity().getSharedPreferences("Address", MODE_PRIVATE)
        adsSpf.edit {
            putString("address", address)
            commit()
        }

//        findNavController().navigate(R.id.action_homeFragment_to_lookFragment)
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_fragment_container, LookFragment()).commitAllowingStateLoss()
//        (context as MainActivity).supportFragmentManager.beginTransaction()
//            .addToBackStack(null)
//            .commit()
    }
}