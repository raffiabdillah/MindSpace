package com.dicoding.mindspace.view.getstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.dicoding.mindspace.R
import com.dicoding.mindspace.databinding.ActivityWelcomeBinding
import com.dicoding.mindspace.view.ViewModelFactory
import com.dicoding.mindspace.view.home.MainActivity
import com.dicoding.mindspace.view.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private val viewModel by viewModels<WelcomeViewModel>() {
        ViewModelFactory.getInstance(this)
    }

    private var isLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSession()
        binding.btnGetStarted.setOnClickListener {
            if (isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }


    private fun getSession() {
        viewModel.getSession().observe(this) {
            isLogin = it != ""
            Log.d("WelcomeActivity", it)
        }
    }

}