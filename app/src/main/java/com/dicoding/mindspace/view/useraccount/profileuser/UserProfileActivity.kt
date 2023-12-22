package com.dicoding.mindspace.view.useraccount.profileuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.mindspace.R
import com.dicoding.mindspace.data.Result
import com.dicoding.mindspace.data.api.userresponse.User
import com.dicoding.mindspace.databinding.ActivityUserProfileBinding
import com.dicoding.mindspace.view.ViewModelFactory
import com.dicoding.mindspace.view.getstarted.WelcomeActivity
import com.dicoding.mindspace.view.login.LoginActivity

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        getProfile()
    }


    private fun getProfile() {
        viewModel.getProfile().observe(this) {
            when(it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    setupView(it.data.user!!)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    Log.d("UserProfileActivity", it.error)
                }
            }
        }
    }

    private fun setupView(user: User) {
        binding.nameProfil.text = user.name
        binding.emailProfil.text = user.email
    }

    private fun setupAction() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}