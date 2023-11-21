package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.core.domain.model.NikModel
import com.digimbanking.Features.Auth.CreateRekening.Card.NomorRekening
import com.digimbanking.R
import com.digimbanking.databinding.ActivityNik2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Nik : AppCompatActivity() {
    private lateinit var binding: ActivityNik2Binding
    lateinit var cifViewModel: CifViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNik2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var data: NikModel? = null

        cifViewModel = ViewModelProvider(this).get(CifViewModel::class.java) // Initialize the ViewModel

        binding.etNIK.editText?.doOnTextChanged { text, start, before, count ->
            data = cifViewModel.validateNik(text.toString())
            if (data != null) {
                binding.etNIK.isErrorEnabled = false
                binding.btnRegist.isEnabled = true
            } else {
                Toast.makeText(this, "Nik tidak terdaftar", Toast.LENGTH_SHORT).show()
                binding.etNIK.error = "Nik tidak terdaftar"
                binding.btnRegist.isEnabled = false
            }
        }

        binding.btnRegist.setOnClickListener {
            if (data != null) {
                startActivity(Intent(this, BuatAkun::class.java).apply {
                    putExtra("dataNik", data)
                })
            } else {
                Toast.makeText(this, "Nik tidak terdaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}