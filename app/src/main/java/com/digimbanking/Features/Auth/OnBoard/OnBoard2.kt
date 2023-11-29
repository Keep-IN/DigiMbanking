package com.digimbanking.Features.Auth.OnBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.Features.Auth.Question.QuestionPage
import com.digimbanking.R
import com.digimbanking.databinding.ActivityLoginBinding
import com.digimbanking.databinding.ActivityOnBoard2Binding
import dagger.hilt.android.AndroidEntryPoint

class OnBoard2 : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoard2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnBoard2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBackOb2.setOnClickListener{
            startActivity(Intent(this, Onboard::class.java))
        }

        binding.ivNextOb2.setOnClickListener {
            startActivity(Intent(this, QuestionPage::class.java))
        }
    }
}