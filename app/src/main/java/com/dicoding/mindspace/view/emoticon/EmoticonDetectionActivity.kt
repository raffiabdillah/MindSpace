package com.dicoding.mindspace.view.emoticon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
import com.dicoding.mindspace.R
import com.dicoding.mindspace.databinding.ActivityEmoticonDetectionBinding
import com.dicoding.mindspace.view.ViewModelFactory
import com.dicoding.mindspace.view.emoticon.stories.StoriesActivity


class EmoticonDetectionActivity : AppCompatActivity() {
    private val viewModel by viewModels<EmoticonDetectionViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityEmoticonDetectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmoticonDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAction()
    }

    private fun setAction() {
        binding.btnSubmit.setOnClickListener {
            val text = binding.textsharingsession.text.toString()

            viewModel.getEmoticon(text)
            viewModel.emot.observe(this) {
                if (it.emotion != null) {
                    val bundle = Bundle().apply {
                        putString(TAG, it.emotion)
                    }
                    val fragment = ResultFragment()
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        binding.btnViewStories.setOnClickListener{
            startActivity(Intent(this, StoriesActivity::class.java))
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment != null) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        } else {
            finish()
            super.onBackPressed()
        }
    }

    companion object {
        const val TAG = "EmotResult"
    }
}