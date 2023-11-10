package com.digimbanking.Features.Auth.AdaRekening.MpinSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.KonfirmasiMPINsdh
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.widget.addTextChangedListener
import com.chaos.view.PinView

class BuatMPINsdh : AppCompatActivity() {
    private lateinit var binding: ActivityBuatMpinsdhBinding
    private lateinit var viewModel:PinViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuatMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PinViewModel::class.java)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val pinView = binding.sendOtp
        pinView.addTextChangedListener {
            it?.let {
                viewModel.setPin(it.toString())
                if (it.length == pinView.itemCount) {
                    val intent = Intent(this, KonfirmasiMPINsdh::class.java)
                    startActivity(intent)
                }
            }
        }

    }
    override fun onPause() {
        super.onPause()
        viewModel.pin.value?.let {
            sharedPreferences.edit().putString("pin", it).apply()
        }
    }

}