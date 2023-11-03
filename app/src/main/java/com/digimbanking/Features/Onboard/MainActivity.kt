package com.digimbanking.Features.Onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.Features.Auth.CreateRekening.Card.PilihKartu
import com.digimbanking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtWorld.setOnClickListener {
            startActivity(Intent(this, PilihKartu::class.java))
        }
    }
}