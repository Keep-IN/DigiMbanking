package com.digimbanking.Features.Auth.CreateRekening.Card

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.core.data.response.auth.createRekening.cif.CifResponse
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KataSandi
import com.digimbanking.databinding.ActivityNomorRekeningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NomorRekening : AppCompatActivity() {
    private lateinit var binding: ActivityNomorRekeningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNomorRekeningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<CifResponse>("nik")

        if (data != null) {
            binding.etNomorRekening.editText?.setText(data.norek)
            binding.btnRegist.isEnabled = true
        } else {
            binding.btnRegist.isEnabled = false
        }

        binding.btnRegist.setOnClickListener {
            if (data != null) {
                startActivity(Intent(this, KataSandi::class.java).apply {
                    putExtra("dataNik", data)
                })
            } else {
                Toast.makeText(this, "Nik tidak terdaftar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.copy.setOnClickListener {
            copyToClipboard()
        }
    }

    private fun copyToClipboard() {
        val editText = binding.etNomorRekening.editText

        if (editText != null) {
            val textToCopy = editText.text.toString()

            if (textToCopy.isNotBlank()) {
                val clipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Teks yang disalin", textToCopy)
                clipboardManager.setPrimaryClip(clipData)

                Toast.makeText(
                    this@NomorRekening,
                    "Nomor rekening telah disalin",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@NomorRekening,
                    "Nomor rekening tidak tersedia",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
