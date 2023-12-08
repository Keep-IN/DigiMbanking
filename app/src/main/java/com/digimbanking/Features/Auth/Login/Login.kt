package com.digimbanking.Features.Auth.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.KeyEvent.DispatcherState
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.domain.model.LoginModel
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogFailLogin
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogSuccessLogin
import com.digimbanking.Features.Auth.OnBoard.Onboard
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.Features.Profile.Profil.FProfil
import com.digimbanking.Features.Transfer.TransferSesama.AlertDialog.AlertDialogGagal
import com.digimbanking.R
import com.digimbanking.databinding.ActivityLoginBinding
import com.digimbanking.databinding.AlertDialogFailLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private  lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this@Login)
        val sPref = sharedPref.edit()
        sPref.clear()
        sPref.apply()

        binding.apply {
            tilLoginEmail.editText?.doOnTextChanged { text, start, before, count ->
                if (loginViewModel.validateEmail(text.toString())) {
                    tilLoginEmail.isErrorEnabled = false
                } else {
                    tilLoginPw.isErrorEnabled = true
                    btnLoginMasuk.isEnabled = false
                    tilLoginEmail.error = "Email harus sesuai format penulisan"
                }
                validateInput()
            }

            tilLoginPw.editText?.doOnTextChanged { text, start, before, count ->
                if (loginViewModel.validatePassword(text.toString())) {
                    binding.tilLoginPw.isErrorEnabled = false
                } else {
                    binding.btnLoginMasuk.isEnabled = false
                }
                validateInput()
            }

            ivBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
                finish()
            }

            btnLoginMasuk.setOnClickListener {
                loginViewModel.viewModelScope.launch(Dispatchers.Main) {
                    onLoading()
                    loginViewModel.login(
                        binding.tilLoginEmail.editText?.text.toString(),
                        binding.tilLoginPw.editText?.text.toString()
                    )
                        .observe(this@Login, Observer { result ->
                            when (result) {
                                is Result.Success -> {
                                    result.data
                                    sPref.putString("token", result.data.data.token)
                                    Log.d("Tes", "token: ${result.data.data.token}")
                                    sPref.apply()
                                    val allertSuccess = AlertDialogSuccessLogin.newInstance(result.data.message)
                                    allertSuccess.show(supportFragmentManager, "success")
                                    onFinishedLoading()
                                }

                                is Result.Error -> {
                                    val allertFailed = AlertDialogFailLogin.newInstance(result.errorMessage)
                                    allertFailed.show(supportFragmentManager, "fail")
                                    onFinishedLoading()
                                }

                                else -> {
                                    Log.d("Tes", "Empty JSON")
                                    onFinishedLoading()
                                }
                            }
                        })
                }
//                data = loginViewModel.validateLogin(binding.tilLoginEmail.editText?.text.toString(),
//                    binding.tilLoginPw.editText?.text.toString())
//                if(data != null){
//                    AlertDialogSuccessLogin().show(supportFragmentManager,"test")
//                } else {
//                    AlertDialogFailLogin().show(supportFragmentManager, "test")
//                }
            }
        }
    }
    private fun validateInput(){
        val email = binding.tilLoginEmail.editText?.text.toString()
        val password = binding.tilLoginPw.editText?.text.toString()

        val isEmailValid = loginViewModel.validateEmail(email)
        val isPasswordValid = loginViewModel.validatePassword(password)

        binding.btnLoginMasuk.isEnabled = isEmailValid && isPasswordValid
    }

    private fun onLoading(){
        binding.flLoading.visibility = View.VISIBLE
    }

    private fun  onFinishedLoading(){
        binding.flLoading.visibility = View.GONE
    }
}