package com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.core.data.network.Result
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.AlertSandi.AlertSandiACC
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.AlertSandi.AlertSandiDEC
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.BuatSandiSudah
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.BuatMPINsdh
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.AlertMPIN.ALERTMPINDEC
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.AlertMPIN.AlertMPINACC
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.PinViewModel
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.databinding.ActivityKonfirmasiMpinsdhBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KonfirmasiMPINsdh : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiMpinsdhBinding
    private lateinit var viewModel: PinViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKonfirmasiMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PinViewModel::class.java)
        sharedPreferences = getSharedPreferences("Mpin", MODE_PRIVATE)
        val pinView = binding.pinPiw
        val pinErrorText = binding.pinError

        binding.btnBackkMPIN.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        binding.pinPiw.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6) {
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    viewModel.konfirmasiPin.observe(this@KonfirmasiMPINsdh, Observer {
                        if (it.length == pinView.itemCount) {
                            val getMpin = sharedPreferences.getString("pin", "")
                            if (it == getMpin) {
                                viewModel.putMPIN(text.toString())
                                    .observe(this@KonfirmasiMPINsdh, Observer { result ->
                                        when (result) {
                                            is Result.Success -> {
                                                val allertSuccess = AlertMPINACC.newInstance(result.data.message)
                                                allertSuccess.show(supportFragmentManager, "success")
                                                onFinishedLoading()
                                            }

                                            is Result.Error -> {
                                                ALERTMPINDEC().show(supportFragmentManager, "no")
                                                ALERTMPINDEC().setErrorMessage(result.errorMessage)
                                                Toast.makeText(this@KonfirmasiMPINsdh, result.errorMessage, Toast.LENGTH_SHORT).show()
                                                onFinishedLoading()
                                            }
                                            is Result.Loading -> {
                                                onFinishedLoading()
                                            }

                                            else -> {
                                                onLoading()
                                            }
                                        }
                                    })
//                                AlertMPINACC().show(supportFragmentManager,"yes")
//                                startActivity(Intent(this@KonfirmasiMPINsdh, Login::class.java))
                            } else {
                                pinView.setLineColor(Color.RED)
                                pinView.setTextColor(Color.RED)
                                pinErrorText.visibility = View.VISIBLE
                            }
                        } else {
                            pinView.setLineColor(Color.parseColor("#6C63FF"))
                            pinView.setTextColor(Color.parseColor("#6C63FF"))
                            pinErrorText.visibility = View.GONE
                        }
                    })

                }
            }
        }
        pinView.addTextChangedListener {
            it?.let {
                viewModel.setKonfirmasiPin(it.toString())
            }
        }

    }
    private fun onLoading(){
        binding.bgLoading.visibility = View.VISIBLE
    }
    private fun onFinishedLoading(){
        binding.bgLoading.visibility = View.GONE
    }
}
