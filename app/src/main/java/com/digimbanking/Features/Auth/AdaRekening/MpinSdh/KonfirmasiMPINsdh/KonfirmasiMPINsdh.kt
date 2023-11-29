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

        viewModel = ViewModelProvider(this).get(viewModel::class.java)
        sharedPreferences = getSharedPreferences("Mpin", MODE_PRIVATE)
        val pinView = binding.pinPiw
        val pinErrorText = binding.pinError

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
                                            is Result.Error -> {
                                                Toast.makeText(
                                                    this@KonfirmasiMPINsdh,
                                                    result.errorMessage,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                            is Result.Loading -> {

                                            }

                                            else -> {

                                            }
                                        }
                                    })
                                val intent = Intent(this@KonfirmasiMPINsdh, Login::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                pinView.setLineColor(Color.RED)
                                pinErrorText.visibility = View.VISIBLE
                            }
                        } else {
                            pinView.setLineColor(Color.parseColor("#6C63FF"))
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
}
