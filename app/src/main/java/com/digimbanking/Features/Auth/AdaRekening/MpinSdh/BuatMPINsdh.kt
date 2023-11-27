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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuatMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PinViewModel::class.java]
        sharedPreferences = getSharedPreferences("pin", MODE_PRIVATE)
        binding.sendMPIN.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    viewModel.putMPIN(text.toString()).observe(this@BuatMPINsdh, Observer { result ->
                        when (result) {
                            is Result.Success -> {
                               val intent = Intent(this@BuatMPINsdh, KonfirmasiMPINsdh::class.java)
                                startActivity(intent)
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
    override fun onPause() {
        super.onPause()
        sharedPreferences.edit().putString("pin", binding.sendMPIN.text.toString()).apply()
    }

}
