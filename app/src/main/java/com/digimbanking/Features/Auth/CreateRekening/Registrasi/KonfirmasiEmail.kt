package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KonfirmasiEmail : AppCompatActivity() {
    private lateinit var viewmodel : RegisViewModel
    lateinit var binding: ActivityKonfirmasiEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfirmasiEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(RegisViewModel::class.java)

        binding.apply {
            etEmail.editText?.doOnTextChanged { text, start, before, count ->
                if (viewmodel.validateEmail(text.toString())){
                    binding.etEmail.isErrorEnabled = false
                } else {
                    binding.etEmail.isErrorEnabled = true
                    binding.etEmail.error = "Email yang dimasukkan harus sesuai format"
                }
                validateInput()
            }
        }

        binding.btnRegist.setOnClickListener {
            startActivity(Intent(this, Otp::class.java))
        }
    }

    private fun validateInput(){
        binding.btnRegist.isEnabled = viewmodel.validateEmail(binding.etEmail.editText?.text.toString())
    }
}