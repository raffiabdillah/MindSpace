package com.dicoding.mindspace.view.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.mindspace.databinding.ActivityLoginBinding
import com.dicoding.mindspace.view.ViewModelFactory
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.dicoding.mindspace.R
import com.dicoding.mindspace.data.Result
import com.dicoding.mindspace.data.api.model.UserLoginModel
import com.dicoding.mindspace.view.customview.EditTextEmail
import com.dicoding.mindspace.view.customview.EditTextPassword
import com.dicoding.mindspace.view.home.MainActivity
import com.dicoding.mindspace.view.register.RegisterActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding
    private lateinit var myEditText: EditTextPassword
    private lateinit var myEditTextEmail: EditTextEmail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myEditText = findViewById(R.id.passwordEditText)
        myEditTextEmail = findViewById(R.id.emailEditText)

        setupView()
        setupAction()
        playAnimation()

        myEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setMyButton()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        myEditTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setMyButton()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun setMyButton(){
        val boleh = myEditText.allowed && myEditTextEmail.allowed
        binding.btnLogin.isEnabled = boleh

        if (!boleh){
            binding.btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.navy))
        } else {
            binding.btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val userLogin = UserLoginModel(email, password)

            viewModel.login(userLogin).observe(this) {
                when (it) {
                    is Result.Loading -> {
                        binding.progressIndicator.visibility = View.VISIBLE
                        binding.btnLogin.isEnabled = false
                    }
                    is Result.Success -> {
                        binding.progressIndicator.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                        AlertDialog.Builder(this).apply {
                            setTitle("Login Success")
                            setMessage("Welcome to MindSpace!")
                            create()
                            show()
                        }
                        viewModel.saveSession(it.data.accessToken!!)
                        intentToMain()
                    }
                    is Result.Error -> {
                        binding.progressIndicator.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                        AlertDialog.Builder(this).apply {
                            setTitle("Login Failed")
                            setMessage(it.error)
                            setPositiveButton("OK") { _, _ -> }
                            create()
                            show()
                        }
                    }
                }
            }
        }
        binding.btnRegisterNow.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun intentToMain() {
        lifecycleScope.launch {
            delay(1000)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
            onDestroy()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.logologin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }

}