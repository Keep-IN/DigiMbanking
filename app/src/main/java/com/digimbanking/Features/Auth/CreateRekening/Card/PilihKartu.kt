package com.digimbanking.Features.Auth.CreateRekening.Card

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KonfirmasiEmail
import com.digimbanking.R
import com.digimbanking.databinding.ActivityPilihKartuBinding

class PilihKartu : AppCompatActivity() {
    private lateinit var binding: ActivityPilihKartuBinding
    private val sharedPrefname = "kartu_preference"
    private val key_tipeKartu = "tipe_kartu"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihKartuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            cardSilver.setStrokeColor(Color.parseColor("#6C63FF"))
            cardGold.setStrokeColor(Color.parseColor("#6C63FF"))
            cardPlatinum.setStrokeColor(Color.parseColor("#6C63FF"))
        }


        val tipeKartu = getTipeKartu()

        binding.cardSilver.setOnClickListener {
            saveTipeKartu("silver")
            binding.apply {
                cardSilver.strokeWidth = 5
                cardGold.strokeWidth = 0
                cardPlatinum.strokeWidth = 0
                validateInput()
            }
        }

        binding.cardGold.setOnClickListener {
            saveTipeKartu("gold")
            binding.apply {
                cardSilver.strokeWidth = 0
                cardGold.strokeWidth = 5
                cardPlatinum.strokeWidth = 0
                validateInput()
            }
        }

        binding.cardPlatinum.setOnClickListener {
            saveTipeKartu("platinum")
            binding.apply {
                cardSilver.strokeWidth = 0
                cardGold.strokeWidth = 0
                cardPlatinum.strokeWidth = 5
                validateInput()
            }
        }

        binding.btnLanjut.setOnClickListener {
            startActivity(Intent(this, KonfirmasiEmail::class.java))

        }

    }


    private fun saveTipeKartu(tipe: String) {
        val sharedPreferences = getSharedPreferences(sharedPrefname, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key_tipeKartu, tipe)
        editor.apply()
    }

    private fun getTipeKartu(): String? {
        val sharedPreferences = getSharedPreferences(sharedPrefname, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key_tipeKartu, null)
    }

    fun validateInput () {
        binding.btnLanjut.isEnabled = getTipeKartu() != null
    }

}