package com.digimbanking.Features.Profile.UbahPw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.Login.LoginViewModel
import com.digimbanking.R
import com.digimbanking.databinding.ActivityUbahPwBinding
import dagger.hilt.android.AndroidEntryPoint


class UbahPw : AppCompatActivity() {
    private lateinit var binding: ActivityUbahPwBinding
    private lateinit var ubahPwViewModel: UbahPwViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUbahPwBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ubahPwViewModel =  ViewModelProvider(this).get(UbahPwViewModel::class.java)

        binding.apply {
            tilPwLama.editText?.doOnTextChanged { text, start, before, count ->
                if(ubahPwViewModel.validatePasswordLama(text.toString())){
                    binding.tilPwLama.isErrorEnabled = false
                    binding.btnSimpanPwBaru.isEnabled = true
                } else {
                    binding.tilPwLama.isErrorEnabled = true
                    binding.btnSimpanPwBaru.isEnabled = false
                    binding.tilPwLama.error = "Kata Sandi harus terdiri dari minimal 8 karakter  "
                }
            }
            tilPwBaru.editText?.doOnTextChanged { text, start, before, count ->
                if(ubahPwViewModel.validatePasswordBaru(text.toString())){
                    binding.tilPwBaru.isErrorEnabled = false
                    binding.btnSimpanPwBaru.isEnabled = true
                } else {
                    binding.tilPwBaru.isErrorEnabled = true
                    binding.btnSimpanPwBaru.isEnabled = false
                    binding.tilPwBaru.error = "Kata Sandi harus terdiri dari minimal 8 karakter  "
                }
            }
            tilKonfirmPwBaru.editText?.doOnTextChanged { text, start, before, count ->
                if(ubahPwViewModel.validatePasswordKonfirm(text.toString())){
                    binding.tilKonfirmPwBaru.isErrorEnabled = false
                    binding.btnSimpanPwBaru.isEnabled = true
                } else {
                    binding.tilKonfirmPwBaru.isErrorEnabled = true
                    binding.btnSimpanPwBaru.isEnabled = false
                    binding.tilKonfirmPwBaru.error = "Kata Sandi harus terdiri dari minimal 8 karakter  "
                }
            }
            validateInput()
            btnSimpanPwBaru.setOnClickListener{
                AlertDialogUbahPwSuccess().show(supportFragmentManager,"test")
            }
        }
    }
    private fun validateInput(){
        val passwordLama = binding.tilPwLama.editText?.text.toString()
        val passwordBaru = binding.tilPwBaru.editText?.text.toString()
        val passwordKonfirm = binding.tilKonfirmPwBaru.editText?.text.toString()

        val isPasswordLamaValid = ubahPwViewModel.validatePasswordLama(passwordLama)
        val isPasswordBaruValid = ubahPwViewModel.validatePasswordBaru(passwordBaru)
        val isPasswordKonfirm = ubahPwViewModel.validatePasswordKonfirm(passwordKonfirm)

        binding.btnSimpanPwBaru.isEnabled = isPasswordLamaValid && isPasswordBaruValid && isPasswordKonfirm
    }
}