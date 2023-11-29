package com.digimbanking.Features.Auth.OnBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.Features.Auth.Question.QuestionPage
import com.digimbanking.Features.Profile.Profil.FProfil
import com.digimbanking.Features.Transfer.Riwayat.Filter.BottomSheetFilterFragment
import com.digimbanking.R
import com.digimbanking.databinding.ActivityOnboardBinding
import dagger.hilt.android.AndroidEntryPoint

class Onboard : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivNextOB1.setOnClickListener{
            startActivity(Intent(this, OnBoard2::class.java))
        }

        binding.tvOnBoardLewati.setOnClickListener {
            startActivity(Intent(this, QuestionPage::class.java))
        }
    }
}