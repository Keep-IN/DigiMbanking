package com.digimbanking.Features.Profile.UbahPw

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.domain.model.LoginModel
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogFailLogin
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogSuccessLogin
import com.digimbanking.Features.Auth.Login.LoginViewModel
import com.digimbanking.R
import com.digimbanking.databinding.ActivityUbahPwBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UbahPw : AppCompatActivity() {
    private lateinit var binding: ActivityUbahPwBinding
    private lateinit var ubahPwViewModel: UbahPwViewModel
    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUbahPwBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        var data: LoginModel? = null

        sharedPref = getSharedPreferences("token", Context.MODE_PRIVATE)
//        val token = sharedPreferences.getString("token", null)
//        if (token != null) {
//            Log.d("isi Token:", token)
//        }

        ubahPwViewModel =  ViewModelProvider(this).get(UbahPwViewModel::class.java)

        binding.apply {
            tilPwLama.editText?.doOnTextChanged { text, start, before, count ->
                if(ubahPwViewModel.validatePasswordLama(text.toString())){
                    binding.tilPwLama.isErrorEnabled = false
                } else {
                    binding.tilPwLama.isErrorEnabled = true
                    binding.btnSimpanPwBaru.isEnabled = false
                    binding.tilPwLama.error = "Kata Sandi harus terdiri dari minimal 8 karakter"
                }
                validateInput()
            }

            tilPwBaru.editText?.doOnTextChanged { text, start, before, count ->
                if(ubahPwViewModel.validatePasswordBaru(text.toString())){
                    if(ubahPwViewModel.validatePasswordBeda(binding.tilPwLama.editText?.text.toString(), binding.tilPwBaru.editText?.text.toString())){
                        binding.tilPwBaru.isErrorEnabled = false
                    } else {
                        binding.tilPwBaru.isErrorEnabled = true
                        binding.btnSimpanPwBaru.isEnabled = false
                        binding.tilPwBaru.error = "Kata Sandi harus berbeda dengan Kata Sandi Lama"
                    }
                } else {
                    binding.tilPwBaru.isErrorEnabled = true
                    binding.btnSimpanPwBaru.isEnabled = false
                    binding.tilPwBaru.error = "Kata Sandi harus terdiri dari minimal 8 karakter"
                }
                validateInput()
            }
            tilKonfirmPwBaru.editText?.doOnTextChanged { text, start, before, count ->
                if(ubahPwViewModel.validatePasswordKonfirm(text.toString())){
                    if(ubahPwViewModel.validatePasswordSama(binding.tilPwBaru.editText?.text.toString(), binding.tilKonfirmPwBaru.editText?.text.toString())){
                        binding.tilKonfirmPwBaru.isErrorEnabled = false
                    } else {
                        binding.tilKonfirmPwBaru.isErrorEnabled = true
                        binding.btnSimpanPwBaru.isEnabled = false
                        binding.tilKonfirmPwBaru.error = "Kata Sandi tidak sama"
                    }
                } else {
                    binding.tilKonfirmPwBaru.isErrorEnabled = true
                    binding.btnSimpanPwBaru.isEnabled = false
                    binding.tilKonfirmPwBaru.error = "Kata Sandi harus terdiri dari minimal 8 karakter"
                }
                validateInput()
            }

            ivBackUbahPw.setOnClickListener{
                onBackPressedDispatcher.onBackPressed()
            }

            btnSimpanPwBaru.setOnClickListener{
                ubahPwViewModel.viewModelScope.launch(Dispatchers.Main) {
                    val token = sharedPref.getString("token", "").toString()
                    ubahPwViewModel.ubahPw(binding.tilPwLama.editText?.text.toString(), binding.tilPwBaru.editText?.text.toString(), binding.tilKonfirmPwBaru.editText?.text.toString())
                        .observe(this@UbahPw){result ->
                            when(result){
                                is Result.Success -> {
                                    result.data
                                    val allertSuccess = AlertDialogUbahPwSuccess.newInstance(result.data.message)
                                    allertSuccess.show(supportFragmentManager, "done")
                                }
                                is Result.Error -> {
                                    binding.tilPwLama.isErrorEnabled = true
                                    binding.tilPwLama.error = "Kata Sandi salah"
                                }
                                else -> {
                                    Log.d("Tes", "Empty JSON")
                                }
                            }
                        }
                }
                finish()

//                data = ubahPwViewModel.validatePasswordLogin(binding.tilPwLama.editText?.text.toString())
//                if(data != null){
//                    AlertDialogUbahPwSuccess().show(supportFragmentManager, "test")
//                } else {
//                    binding.tilPwLama.isErrorEnabled = true
//                    binding.btnSimpanPwBaru.isEnabled = false
//                    binding.tilPwLama.error = "Kata Sandi salah"
//                }
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