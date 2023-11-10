package com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chaos.view.PinView
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import com.digimbanking.databinding.ActivityKonfirmasiMpinsdhBinding

class KonfirmasiMPINsdh : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiMpinsdhBinding
    private lateinit var viewModel: KonfirmasiMPINViewModelsdh
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKonfirmasiMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(KonfirmasiMPINViewModelsdh::class.java)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val pinView = binding.pinPiw
        val pinErrorText = binding.pinError

        viewModel.konfirmasiPin.observe(this, Observer {
            if (it.length == pinView.itemCount) {
                val pinFromFirstActivity = sharedPreferences.getString("pin", "")
                if (it == pinFromFirstActivity) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    pinView.setLineColor(Color.RED)
                    pinErrorText.visibility = View.VISIBLE

                }
            }else {
                    pinView.setLineColor(Color.parseColor("#6C63FF"))
                    pinErrorText.visibility = View.GONE
            }
        })

        pinView.addTextChangedListener {
            it?.let {
                viewModel.setKonfirmasiPin(it.toString())
            }
        }
    }
}