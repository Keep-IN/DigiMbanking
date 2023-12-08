package com.digimbanking.Features.Auth.Question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.Features.Auth.OnBoard.OnBoard2
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiRekSudahBinding
import com.digimbanking.databinding.ActivityQuestionPageBinding
import dagger.hilt.android.AndroidEntryPoint


class QuestionPage : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuestionPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnDaftarAkun.setOnClickListener {
                val bottomSheetSudahPunyaAkun = BottomSheetSudahPunyaAkun()
                bottomSheetSudahPunyaAkun.show(supportFragmentManager, bottomSheetSudahPunyaAkun.tag)
            }

            tvSudahPunyaAkun.setOnClickListener {
                startActivity(Intent(this@QuestionPage, Login::class.java))
            }

            ivBackQuestion.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
                finish()
            }
        }
    }
}