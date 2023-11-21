package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.core.domain.model.NikModel
import com.core.domain.model.OtpModel
import com.digimbanking.Features.Auth.CreateRekening.Cif.Nik
import com.digimbanking.Features.Auth.CreateRekening.Mpin.KonfirmasiMpin
import com.digimbanking.Features.Auth.CreateRekening.Mpin.MpinViewModel
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Otp : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var otpViewModel: RegisViewModel
    private lateinit var timer: CountDownTimer
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        otpViewModel = ViewModelProvider(this).get(RegisViewModel::class.java)

        otpViewModel.generateRandomOtp()
        sharedPreferences = getSharedPreferences("Otp", MODE_PRIVATE)

        otpViewModel.otpCodeLiveData.observe(this, Observer { otpCode ->
            binding.sendOtp.setText(otpCode)
            Handler(Looper.getMainLooper()).postDelayed({
                navigateToKonfrek()
            }, 2000)
        })

    }

    private fun navigateToKonfrek() {
        val intent = Intent(this, Nik::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        val totalTimeMillis: Long = 2 * 60 * 1000 + 30 * 1000// 2 minutes 30 sec in milliseconds

        timer = object : CountDownTimer(totalTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val time = String.format("%02d:%02d", minutes % 60, seconds % 60)
                binding.etWaktu.text = time
            }

            override fun onFinish() {
                binding.etWaktu.text = "00:00"
            }
        }
        timer.start()
    }
}