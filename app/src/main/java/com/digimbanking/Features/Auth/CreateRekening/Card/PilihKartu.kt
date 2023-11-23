package com.digimbanking.Features.Auth.CreateRekening.Card

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.pilihKartu.CardResponse
import com.core.data.response.auth.createRekening.pilihKartu.DataCard
import com.digimbanking.Features.Auth.CreateRekening.Cif.Nik
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KonfirmasiEmail
import com.digimbanking.R
import com.digimbanking.databinding.ActivityPilihKartuBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PilihKartu : AppCompatActivity() {
    private lateinit var binding: ActivityPilihKartuBinding
    private lateinit var cardViewmodel : CardViewModel
    private lateinit var sharedPref : SharedPreferences
    private val sharedPrefname = "kartu_preference"
    private val key_tipeKartu = "tipe_kartu"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihKartuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardViewmodel = viewModels<CardViewModel>().value

        cardViewmodel.viewModelScope.launch(Dispatchers.Main) {
            cardViewmodel.getCard().observe(this@PilihKartu) {
                when (it) {
                    is Result.Success -> {
                        setupView(it.data)
                    }
                    is Result.Error -> {
                        Toast.makeText(this@PilihKartu, "${it.errorMessage}", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.d("Tes", "Empty JSON")
                    }
                }
            }
        }


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

    private fun setupView(data: CardResponse) {
        data.data.forEach {
            binding.apply {
                if (it.idTipe == 2) {
                    txtSilver.text = it.namaTipe
                    txtLimit5.text = "Limit transfer: ${it.limitTransfer}"
                } else if (it.idTipe == 1) {
                    txtGold.text = it.namaTipe
                    txtLimit10.text = "Limit transfer: ${it.limitTransfer}"
                } else if (it.idTipe == 3 ){
                    txtPlatinum.text = it.namaTipe
                    txtLimit15.text = "Limit transfer: ${it.limitTransfer}"
                } else {

                }
            }
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