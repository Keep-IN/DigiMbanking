package com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatSandiSudahBinding
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding

class BuatSandiSudah : AppCompatActivity() {
    private lateinit var binding: ActivityBuatSandiSudahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuatSandiSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}