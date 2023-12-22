package com.dicoding.mindspace.view.emoticon.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.mindspace.R
import com.dicoding.mindspace.databinding.ActivityDetailStoryBinding
import com.dicoding.mindspace.view.formatDate

class DetailStoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val date = intent.getStringExtra(DATE).toString()
        val text = intent.getStringExtra(TEXT).toString()
        val emot = intent.getStringExtra(EMOT).toString()
        val img : Int = if (emot == "Sad"){
            R.drawable.sad_small
        } else {
            R.drawable.happy_small
        }

        binding.createAt2.text = formatDate(date)
        binding.tvCurhatan.text= text
        binding.emotResult.setImageResource(img)
    }

    companion object{
        const val DATE = "DATE"
        const val EMOT = "EMOT"
        const val TEXT = "TEXT"
    }
}