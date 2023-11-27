package com.digimbanking.Features.Auth.AdaRekening.MpinSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.KonfirmasiMPINsdh
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.chaos.view.PinView
import com.core.data.network.Result
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfirmasiRekSudah
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuatMPINsdh : AppCompatActivity() {
    private lateinit var binding: ActivityBuatMpinsdhBinding
    private lateinit var viewModel:PinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuatMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PinViewModel::class.java]
        binding.sendMPIN.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    viewModel.putOtp(text.toString()).observe(this@BuatMPINsdh, Observer { result ->
                        when (result) {
                            is Result.Success -> {
                               navigateToKonf()
                                intent.putExtra("pin", text.toString())
                            }

                            is Result.Error -> {
                                Toast.makeText(this@BuatMPINsdh, result.errorMessage, Toast.LENGTH_SHORT).show()
                            }

                            is Result.Loading -> {

                            }
                        }
                    })
                }

            }
        }

    }

    private fun navigateToKonf() {
        val intent = Intent(this, KonfirmasiMPINsdh::class.java)
        startActivity(intent)
        finish()
    }

}