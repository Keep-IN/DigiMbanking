package com.digimbanking.Features.Onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.Features.Auth.CreateRekening.Card.PilihKartu
import com.digimbanking.Features.Auth.CreateRekening.Cif.BottomSheetPekerjaan
import com.digimbanking.Features.Auth.CreateRekening.Cif.BuatAkun
import com.digimbanking.Features.Auth.CreateRekening.Cif.Nik
import com.digimbanking.Features.Auth.CreateRekening.Mpin.BuatMpin
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KataSandi
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KonfirmasiEmail
import com.digimbanking.R
import com.digimbanking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.etWord.setOnClickListener {
            startActivity(Intent(this, PilihKartu::class.java))
        }
    }
}