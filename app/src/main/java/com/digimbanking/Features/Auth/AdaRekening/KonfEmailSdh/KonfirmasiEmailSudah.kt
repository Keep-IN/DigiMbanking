package com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.OtpEmailSudah
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import com.google.android.material.textfield.TextInputLayout

class KonfirmasiEmailSudah : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiEmailSudahBinding
    private lateinit var viewModel: KonfirmasiEmailsdhViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityKonfirmasiEmailSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(KonfirmasiEmailsdhViewModel::class.java)
        binding.btnCheckrek.isEnabled = false
        binding.etCheckrek.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                val isValid = viewModel.isEmailValid(email)
                binding.btnCheckrek.isEnabled = isValid

                if (!isValid) {
                    binding.etBuatakun.error = "Format email tidak valid"
                } else {
                    binding.etBuatakun.error = null
                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnCheckrek.setOnClickListener {
            startActivity(Intent(this, OtpEmailSudah::class.java))
        }
    }
}