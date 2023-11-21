package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.CreateRekening.Mpin.BuatMpin
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKataSandiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KataSandi : AppCompatActivity() {
    lateinit var binding: ActivityKataSandiBinding
    private lateinit var viewmodel : RegisViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKataSandiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(RegisViewModel::class.java)

        binding.apply {
            etPassword.editText?.doOnTextChanged { text, start, before, count ->
                if (viewmodel.validatePassword(text.toString())){
                    binding.etPassword.isErrorEnabled = false
                } else {
                    binding.etPassword.isErrorEnabled = true
                    binding.etPassword.error = "Password harus terdiri dari huruf dan angka"
                }
                validateInput()
            }
            etKonfPw.editText?.doOnTextChanged { text, start, before, count ->
                validateInput()
            }
        }

        binding.btnRegist.setOnClickListener {
            if (validateInput() != null) {
                startActivity(Intent(this, BuatMpin::class.java))
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(){
        val password = binding.etPassword.editText?.text.toString()
        val confirmPassword = binding.etKonfPw.editText?.text.toString()

        val isPasswordValid = viewmodel.validatePassword(password)
        val isPasswordMatch = password == confirmPassword

        binding.btnRegist.isEnabled = isPasswordValid && isPasswordMatch

        if (!isPasswordMatch) {
            binding.etKonfPw.isErrorEnabled = true
            binding.etKonfPw.error = "Kata Sandi tidak sama"
        } else {
            binding.etKonfPw.isErrorEnabled = false
        }

    }
}