package com.digimbanking.Features.Auth.Question

import android.content.Intent
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

        binding.apply {
//            btnDaftarAkun.setOnClickListener {
//                startActivity(Intent(activity, EditProfile::class.java))
//            }
            tvSudahPunyaAkun.setOnClickListener {
                val bottomSheetSudahPunyaAkun = BottomSheetSudahPunyaAkun()
                bottomSheetSudahPunyaAkun.show(supportFragmentManager, bottomSheetSudahPunyaAkun.tag)
            }
        }
    }
}