package com.dicoding.mindspace.view.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mindspace.databinding.ActivityMainBinding
import com.dicoding.mindspace.view.ViewModelFactory
import com.dicoding.mindspace.view.emoticon.EmoticonDetectionActivity
import com.dicoding.mindspace.view.sharingsession.SharingSessionActivity
import com.dicoding.mindspace.view.sharingsession.SharingSessionAdapter
import com.dicoding.mindspace.view.useraccount.profileuser.UserProfileActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.btnAccount.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }
        binding.btnEmoticon.setOnClickListener{
            startActivity(Intent(this, EmoticonDetectionActivity::class.java))
        }

        binding.imgSharingsession.setOnClickListener {
            startActivity(Intent(this, SharingSessionActivity::class.java))
        }

        viewModel.getStory()
        viewModel.emot.observe(this){
            val adapter = SharingSessionAdapter()
            binding.rvMain.layoutManager = LinearLayoutManager(this)
            binding.rvMain.setHasFixedSize(true)
            binding.rvMain.adapter = adapter
            adapter.submitList(it)
        }
    }
}