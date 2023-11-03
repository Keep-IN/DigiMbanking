package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import com.digimbanking.databinding.ActivityKonfirmasiRekSudahBinding

class KonfirmasiRekSudah : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiRekSudahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityKonfirmasiRekSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}