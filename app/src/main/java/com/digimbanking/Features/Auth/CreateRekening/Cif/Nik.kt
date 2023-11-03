package com.digimbanking.Features.Auth.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private  lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.apply {
//            tilEmail.editText?.doOnTextChanged { text, start, before, count ->
//                if (loginViewModel.validateEmail(text.toString())){
//                    binding.tilEmail.isErrorEnabled = false
//                    binding.btnLogin.isEnabled = true
//                } else {
//                    binding.tilEmail.isErrorEnabled = true
//                    binding.btnLogin.isEnabled = false
//                    binding.tilEmail.error = "Email harus sesuai format penulisan"
//                }
//            }
//            tilPassword.editText?.doOnTextChanged { text, start, before, count ->
//                if (loginViewModel.validatePassword(text.toString())){
//                    binding.tilPassword.isErrorEnabled = false
//                    binding.btnLogin.isEnabled = true
//                } else {
//                    binding.tilPassword.isErrorEnabled = true
//                    binding.btnLogin.isEnabled = false
//                    binding.tilPassword.error = "Kata Sandi harus terdiri dari minimal 8 karakter"
//                }
//            }
        }
    }
}