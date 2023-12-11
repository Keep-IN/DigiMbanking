package com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.AlertSandi.AlertSandiACC
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.AlertSandi.AlertSandiDEC
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.AlertDialog.AlertUnregsdh
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.BottomSheetKonfRek
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfRekViewModelsdh
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfirmasiRekSudah
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.BuatMPINsdh
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.AlertDialogOtpsdh.AlertBerhasilOTP
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.AlertDialogOtpsdh.AlertUnvalidOTPsdh
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatSandiSudahBinding
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuatSandiSudah : AppCompatActivity() {
    private lateinit var binding: ActivityBuatSandiSudahBinding
    private lateinit var viewmodel : KonfRekViewModelsdh
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuatSandiSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(KonfRekViewModelsdh::class.java)
        binding.cvBacksan.setOnClickListener{
            startActivity(Intent(this, KonfirmasiRekSudah::class.java))
        }

        binding.apply {
            etSandibr.editText?.doOnTextChanged { text, start, before, count ->
                if (viewmodel.validatePassword(text.toString())) {
                    binding.etSandibr.isErrorEnabled = false
                } else {
                    binding.etSandibr.isErrorEnabled = true
                    binding.etSandibr.error = "Password harus terdiri dari huruf dan angka"
                }
            }
            etKonfSandi.editText?.doOnTextChanged { text, start, before, count ->
                validateInput()
            }
        }

        binding.btnSimpan.setOnClickListener {
            val password = binding.etSandibr.editText?.text?.toString() ?: ""

            viewmodel.viewModelScope.launch(Dispatchers.Main) {
                viewmodel.putPass(password).observe(this@BuatSandiSudah, Observer { result ->
                    when (result) {
                        is Result.Success -> {
                            val allertSuccess = AlertSandiACC.newInstance(result.data.message)
                            allertSuccess.show(supportFragmentManager, "success")


                        }

                        is Result.Error -> {
                            val allertGagal = AlertSandiDEC.newInstance(result.errorMessage)
                            allertGagal.show(supportFragmentManager, "no")
                        }

                        is Result.Loading -> {

                        }
                    }
                })
            }
        }
    }

    private fun validateInput(){
        val password = binding.etSandibr.editText?.text.toString()
        val confirmPassword = binding.etKonfSandi.editText?.text.toString()

        val isPasswordValid = viewmodel.validatePassword(password)
        val isPasswordMatch = password == confirmPassword

        binding.btnSimpan.isEnabled = isPasswordValid && isPasswordMatch

        if (!isPasswordMatch) {
            binding.etKonfSandi.isErrorEnabled = true
            binding.etKonfSandi.error = "Kata Sandi tidak sama"
        } else {
            binding.etKonfSandi.isErrorEnabled = false
        }

    }
}
