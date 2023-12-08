package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.digimbanking.Features.Auth.CreateRekening.Cif.Nik
import com.core.data.network.Result
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.AlertOtp.OtpFailed
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.AlertOtp.OtpSuccess
import com.digimbanking.databinding.ActivityOtpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Otp : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var otpViewModel: RegisViewModel
    private lateinit var timer: CountDownTimer
    private var isTimerFinished = false
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        otpViewModel = ViewModelProvider(this).get(RegisViewModel::class.java)
        binding.kirimUlang.isEnabled = !isTimerFinished
        binding.kirimUlang.setOnClickListener {
            regenerateOtp()
        }

        binding.sendOtp.doOnTextChanged { text, start, before, count ->
            if (text?.length == 4){
                otpViewModel.viewModelScope.launch(Dispatchers.Main) {
                    otpViewModel.checkOtp(text.toString()).observe(this@Otp, Observer { result ->
                        when (result) {
                            is Result.Success -> {
                                val allertSuccess = OtpSuccess.newInstance(result.data.message)
                                allertSuccess.show(supportFragmentManager, "success")
                            }

                            is Result.Error -> {
                                val allertFailed = OtpFailed.newInstance(result.errorMessage)
                                allertFailed.show(supportFragmentManager, "failed")
                            }

                            is Result.Loading -> {

                            }
                        }
                    })
                }

            }
        }

    }

    private fun regenerateOtp() {
        otpViewModel.viewModelScope.launch(Dispatchers.Main) {
            otpViewModel.regenOtp().observe(this@Otp, Observer { result ->
                when(result) {
                    is Result.Success -> {
                        Toast.makeText(this@Otp, "OTP regenerated successfully", Toast.LENGTH_SHORT).show()
                    }

                    is Result.Error -> {
                        Toast.makeText(this@Otp, result.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                    }
                }
            })
        }
    }

//    private fun navigateToKonfrek() {
//        val intent = Intent(this, Nik::class.java)
//        startActivity(intent)
//        finish()
//    }

    override fun onStart() {
        super.onStart()

        val totalTimeMillis: Long = 120 * 1000// 2 minutes 30 sec in milliseconds

        timer = object : CountDownTimer(totalTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val time = String.format("%02d:%02d", minutes % 60, seconds % 60)
                binding.etWaktu.text = time
            }

            override fun onFinish() {
                binding.etWaktu.text = "00:00"
                binding.kirimUlang.isEnabled = true
                isTimerFinished = true
            }
        }
        timer.start()
        binding.kirimUlang.isEnabled = false
    }
}