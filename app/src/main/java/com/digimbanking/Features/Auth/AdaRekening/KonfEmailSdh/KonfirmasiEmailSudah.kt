package com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding

class KonfirmasiEmailSudah : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiEmailSudahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityKonfirmasiEmailSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}