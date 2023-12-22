package com.dicoding.mindspace.view.emoticon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.mindspace.R
import com.dicoding.mindspace.databinding.FragmentResultBinding
import com.dicoding.mindspace.view.emoticon.stories.StoriesActivity

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emot = arguments?.getString(EmoticonDetectionActivity.TAG)

        if (emot == "Sad") {
            binding.ivEmot.setImageResource(R.drawable.sad)
        } else {
            binding.ivEmot.setImageResource(R.drawable.happy)
        }

        binding.btnStories.setOnClickListener {
            startActivity(Intent(requireContext(), StoriesActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}