package com.digimbanking.Features.Auth.Question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiRekSudahBinding
import com.digimbanking.databinding.ActivityQuestionPageBinding

class QuestionPage : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuestionPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}