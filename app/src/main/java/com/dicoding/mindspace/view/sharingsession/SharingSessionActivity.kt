package com.dicoding.mindspace.view.sharingsession

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mindspace.R
import com.dicoding.mindspace.databinding.ActivitySharingsessionBinding
import com.dicoding.mindspace.view.ViewModelFactory
import com.dicoding.mindspace.view.emoticon.stories.StoriesAdapter
import com.dicoding.mindspace.view.emoticon.stories.StoriesViewModel
import com.dicoding.mindspace.view.useraccount.profileuser.UserProfileActivity

class SharingSessionActivity : AppCompatActivity() {
    private val viewModel by viewModels<SharingSessionViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding : ActivitySharingsessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharingsessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getStory()
        viewModel.emot.observe(this){
            val adapter = SharingSessionAdapter()
            binding.rvSharingSession.layoutManager = LinearLayoutManager(this)
            binding.rvSharingSession.setHasFixedSize(true)
            binding.rvSharingSession.adapter = adapter
            adapter.submitList(it)
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }
    }
}