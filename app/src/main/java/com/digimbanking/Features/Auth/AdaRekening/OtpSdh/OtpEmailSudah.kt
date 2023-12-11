package com.digimbanking.Features.Auth.AdaRekening.OtpSdh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh.KonfirmasiEmailSudah
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfirmasiRekSudah
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.AlertMPIN.ALERTMPINDEC
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.AlertDialogOtpsdh.AlertBerhasilOTP
import com.digimbanking.Features.Auth.AdaRekening.OtpSdh.AlertDialogOtpsdh.AlertUnvalidOTPsdh
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import com.digimbanking.databinding.ActivityOtpEmailSudahBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OtpEmailSudah : AppCompatActivity() {
    private lateinit var binding: ActivityOtpEmailSudahBinding
    private lateinit var timer: CountDownTimer
    private var isTimerFinished = false
    private lateinit var viewModel: OtpEmailViewModelsdh
    
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOtpEmailSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[OtpEmailViewModelsdh::class.java]
        binding.tvRegenOTP.isEnabled = !isTimerFinished
        binding.tvRegenOTP.setOnClickListener {
            regenerateOtp()
        }
        binding.sendOtp.doOnTextChanged { text, start, before, count ->
            if (text?.length == 4){
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    viewModel.checkOtp(text.toString()).observe(this@OtpEmailSudah, Observer { result ->
                        when (result) {
                            is Result.Success -> {
                                val allertSuccess = AlertBerhasilOTP.newInstance(result.data.message)
                                allertSuccess.show(supportFragmentManager, "success")
                            }

                            is Result.Error -> {
                                val allertGagal = AlertUnvalidOTPsdh.newInstance(result.errorMessage)
                                allertGagal.show(supportFragmentManager, "no")
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
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.regenOtp().observe(this@OtpEmailSudah, Observer { result ->
                when (result) {
                    is Result.Success -> {
                        Toast.makeText(this@OtpEmailSudah, "OTP regenerated successfully", Toast.LENGTH_SHORT).show()
                        resetTimer()
                    }

                    is Result.Error -> {
                        Toast.makeText(this@OtpEmailSudah, result.errorMessage, Toast.LENGTH_SHORT).show()
                    }

                    is Result.Loading -> {
                    }
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()

        val totalSeconds: Long = 120

        timer = object : CountDownTimer(totalSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val time = String.format("%02d:%02d", minutes % 60, seconds % 60)
                binding.tvTimerOTP.text = time
            }

            override fun onFinish() {
                isTimerFinished = true
                binding.tvRegenOTP.isEnabled = true
                binding.tvTimerOTP.text = "00:00"
            }
        }
        timer.start()
        binding.tvRegenOTP.isEnabled = false
    }
    private fun resetTimer() {
        timer.cancel()
        val totalSeconds: Long = 120
        timer = object : CountDownTimer(totalSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val time = String.format("%02d:%02d", minutes % 60, seconds % 60)
                binding.tvTimerOTP.text = time
            }

            override fun onFinish() {
                isTimerFinished = true
                binding.tvRegenOTP.isEnabled = true
                binding.tvTimerOTP.text = "00:00"
            }
        }
        timer.start()
        binding.tvRegenOTP.isEnabled = false
    }
}