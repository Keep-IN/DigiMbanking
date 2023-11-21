package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityInputNominalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputNominal : AppCompatActivity() {
    private lateinit var binding: ActivityInputNominalBinding
    private var dataNama: String? = null
    private var dataRekening: String? = null
    private var dataBank: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNominalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataNama = intent.getStringExtra("nama")
        dataBank = intent.getStringExtra("bank")
        dataRekening = intent.getStringExtra("rekening")

        binding.apply {
            tvInitialUserName.text = dataNama?.first().toString()
            tvNamaNasabah.text = dataNama
            tvRekeningNasabah.text = "${capitalizeWords(dataBank)} - ${dataRekening}"
        }

        binding.btnBack1.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnToMpin.setOnClickListener {
            startActivity(Intent(this, MpinSesama::class.java))
        }
    }
    fun capitalizeWords(input: String?): String{
        val words = input?.split(" ")
        val capitalizedWords = words?.map { it.capitalize() }
        var output: String = ""
        if (capitalizedWords != null) {
            output =  capitalizedWords.joinToString(" ")
        }
        return output
    }
}