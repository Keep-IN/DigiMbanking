package com.digimbanking.Features.Auth.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private  lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.apply {
            tilLoginEmail.editText?.doOnTextChanged{ text, start, before, count ->
                if(loginViewModel.validateEmail(text.toString())){
                    binding.tilLoginEmail.isErrorEnabled = false
                    binding.btnLoginMasuk.isEnabled = true
                } else {
                    binding.tilLoginEmail.isErrorEnabled = true
                    binding.btnLoginMasuk.isEnabled = false
                    binding.tilLoginEmail.error = "Email harus sesuai format penulisan"
                }
            }
            tilLoginPw.editText?.doOnTextChanged { text, start, before, count ->
                if(loginViewModel.validatePassword(text.toString())){
                    binding.tilLoginPw.isErrorEnabled = false
                    binding.btnLoginMasuk.isEnabled = true
                } else {
                    binding.tilLoginPw.isErrorEnabled = true
                    binding.btnLoginMasuk.isEnabled = false
                    binding.tilLoginPw.error = "Kata Sandi harus terdiri dari minimal 8 karakter"
                }
            }
        }
        validateInput()
//        binding.btnLoginMasuk.setOnClickListener{
//            startActivity(Intent(activity, dashboard))
//        }
    }
    private fun validateInput(){
        val email = binding.tilLoginEmail.editText?.text.toString()
        val password = binding.tilLoginPw.editText?.text.toString()

        val isEmailValid = loginViewModel.validateEmail(email)
        val isPasswordValid = loginViewModel.validatePassword(password)

        binding.btnLoginMasuk.isEnabled = isEmailValid && isPasswordValid
    }
}