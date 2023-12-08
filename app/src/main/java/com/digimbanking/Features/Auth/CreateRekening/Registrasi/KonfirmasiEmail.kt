package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.digimbanking.Features.Auth.CreateRekening.Card.PilihKartu
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KonfirmasiEmail : AppCompatActivity() {
    private lateinit var viewmodel : RegisViewModel
    lateinit var binding: ActivityKonfirmasiEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfirmasiEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(RegisViewModel::class.java)
        binding.btnRegist.setOnClickListener {
            viewmodel.viewModelScope.launch(Dispatchers.Main) {
                viewmodel.doOtp(binding.etEmail.editText?.text.toString())
                    .observe(this@KonfirmasiEmail) {
                        when (it) {
                            is Result.Success -> {
                                it.data
                                Log.d("Tes", "${it.data}")
                                startActivity(Intent(this@KonfirmasiEmail, Otp::class.java))
                                onFinishedLoading()
                            }

                            is Result.Error -> {
                                Toast.makeText(
                                    this@KonfirmasiEmail,
                                    "${it.errorMessage}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onFinishedLoading()
                                Log.d("Error Post", it.errorMessage)
                            }

                            else -> {
                                Log.d("Unexpected Result", "Received an unexpected result: $it")
                                onLoading()
                            }
                        }
                    }
            }
        }

        binding.apply {
            etEmail.editText?.doOnTextChanged { text, start, before, count ->
                if (viewmodel.validateEmail(text.toString())){
                    binding.etEmail.isErrorEnabled = false
                } else {
                    binding.etEmail.isErrorEnabled = true
                    binding.etEmail.error = "Email harus sesuai format penulisan"
                }
                validateInput()
            }
        }

        binding.btnKembali.setOnClickListener {
            startActivity(Intent(this, PilihKartu::class.java))
        }


    }

    private fun validateInput(){
        binding.btnRegist.isEnabled = viewmodel.validateEmail(binding.etEmail.editText?.text.toString())
    }

    private fun onLoading(){
        binding.loadScreen.visibility = View.VISIBLE
    }

    private fun onFinishedLoading(){
        binding.loadScreen.visibility = View.GONE
    }
}