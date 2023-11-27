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
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chaos.view.PinView
import com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh.KonfirmasiEmailSudah
import com.digimbanking.Features.Auth.OnBoard.Onboard
import com.digimbanking.Features.Auth.Question.QuestionPage
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import com.digimbanking.databinding.ActivityKonfirmasiMpinsdhBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KonfirmasiMPINsdh : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiMpinsdhBinding
    private lateinit var viewModel: KonfirmasiMPINViewModelsdh
    private lateinit var sharedPreferences: SharedPreferences
    private var pinFromFirstActivity: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityKonfirmasiMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[KonfirmasiMPINViewModelsdh::class.java]
        sharedPreferences = getSharedPreferences("pin", MODE_PRIVATE)

        val pinView = binding.pinPiw
        val pinErrorText = binding.pinError
            viewModel.konfirmasiPin.observe(this, Observer {
                if (it.length == 6) {
                    val pinFromFirstActivity = sharedPreferences.getString("pin", "")
                    if (it == pinFromFirstActivity) {
                        val intent = Intent(this, QuestionPage::class.java)
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
