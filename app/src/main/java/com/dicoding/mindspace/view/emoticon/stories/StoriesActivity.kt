package com.dicoding.mindspace.view.emoticon.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mindspace.R
import com.dicoding.mindspace.databinding.ActivityStoriesBinding
import com.dicoding.mindspace.view.ViewModelFactory

class StoriesActivity : AppCompatActivity() {

    private val viewModel by viewModels<StoriesViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding : ActivityStoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getStory()
        viewModel.emot.observe(this){
            val adapter = StoriesAdapter()
            binding.rvStories.layoutManager = LinearLayoutManager(this)
            binding.rvStories.setHasFixedSize(true)
            binding.rvStories.adapter = adapter
            adapter.submitList(it)
        }
    }
}