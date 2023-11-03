package com.digimbanking.Features.Auth.CreateRekening.Card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KonfirmasiEmail
import com.digimbanking.databinding.ActivityPilihKartuBinding

class PilihKartu : AppCompatActivity() {
    private lateinit var binding: ActivityPilihKartuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihKartuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardSilver.setOnClickListener {
            startActivity(Intent(this, KonfirmasiEmail::class.java))
        }
    }
}