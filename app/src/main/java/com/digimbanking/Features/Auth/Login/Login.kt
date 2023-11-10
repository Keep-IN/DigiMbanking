package com.digimbanking.Features.Auth.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.core.domain.model.LoginModel
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogFailLogin
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogSuccessLogin
import com.digimbanking.Features.Auth.OnBoard.Onboard
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.Features.Transfer.TransferSesama.AlertDialog.AlertDialogGagal
import com.digimbanking.databinding.ActivityLoginBinding
import com.digimbanking.databinding.AlertDialogFailLoginBinding
import dagger.hilt.android.AndroidEntryPoint

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private  lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var data: LoginModel? = null
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.apply {
            tilLoginEmail.editText?.doOnTextChanged{ text, start, before, count ->
                if(loginViewModel.validateEmail(text.toString())){
                    binding.tilLoginEmail.isErrorEnabled = false
                    binding.btnLoginMasuk.isEnabled = true
                } else {
                    binding.tilLoginPw.isErrorEnabled = true
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
            btnLoginMasuk.setOnClickListener{
                data = loginViewModel.validateLogin(binding.tilLoginEmail.editText?.text.toString(), binding.tilLoginPw.editText?.text.toString())
                if(data != null){
                    AlertDialogSuccessLogin().show(supportFragmentManager,"test")
                } else {
                    AlertDialogFailLogin().show(supportFragmentManager, "test")
                }
            }
            validateInput()
        }
    }
    private fun validateInput(){
        val email = binding.tilLoginEmail.editText?.text.toString()
        val password = binding.tilLoginPw.editText?.text.toString()

        val isEmailValid = loginViewModel.validateEmail(email)
        val isPasswordValid = loginViewModel.validatePassword(password)

        binding.btnLoginMasuk.isEnabled = isEmailValid && isPasswordValid
    }
}