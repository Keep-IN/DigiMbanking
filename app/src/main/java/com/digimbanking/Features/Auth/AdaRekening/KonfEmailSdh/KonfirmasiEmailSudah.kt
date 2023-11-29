package com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.core.data.network.Result
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.OtpEmailSudah
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.OtpEmailViewModelsdh
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.Otp
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@AndroidEntryPoint
class KonfirmasiEmailSudah : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiEmailSudahBinding
    private lateinit var viewModel: OtpEmailViewModelsdh
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityKonfirmasiEmailSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(OtpEmailViewModelsdh::class.java)
        binding.btnCheckrek.setOnClickListener {
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                viewModel.otpSent(binding.etBuatakun.editText?.text.toString())
                    .observe(this@KonfirmasiEmailSudah) {
                        when (it) {
                            is Result.Success -> {
                                it.data
                                Log.d("Tes", "${it.data}")
                                startActivity(Intent(this@KonfirmasiEmailSudah, OtpEmailSudah::class.java))
                            }

                            is Result.Error -> {
                                Toast.makeText(
                                    this@KonfirmasiEmailSudah,
                                    "${it.errorMessage}",
                                    Toast.LENGTH_SHORT

                                ).show()
                                Log.d("konfirmasiemailsudah",it.errorMessage)
                            }

                            else -> {
                                Log.d("Tes", "Empty JSON")
                            }
                        }
                    }
            }

        }
        binding.apply {
            etBuatakun.editText?.doOnTextChanged { text, start, before, count ->
                if (viewModel.isEmailValid(text.toString())){
                    binding.etBuatakun.isErrorEnabled = false
                } else {
                    binding.etBuatakun.isErrorEnabled = true
                    binding.etBuatakun.error = "Email yang dimasukkan harus sesuai format"
                }
                validateInput()
            }
        }

//        binding.btnCheckrek.isEnabled = false
//        binding.etCheckrek.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                val email = s.toString()
//                val isValid = viewModel.isEmailValid(email)
//                binding.btnCheckrek.isEnabled = isValid
//
//                if (!isValid) {
//                    binding.etBuatakun.error = "Format email tidak valid"
//                } else {
//                    binding.etBuatakun.error = null
//                }
//
//            }
//
//            override fun afterTextChanged(s: Editable?) {}
//        })

    }
    private fun validateInput(){
        binding.btnCheckrek.isEnabled = viewModel.isEmailValid(binding.etBuatakun.editText?.text.toString())
    }
}
