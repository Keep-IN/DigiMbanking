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
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfirmasiRekSudah
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
    private lateinit var viewModel: OtpEmailViewModelsdh
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOtpEmailSudahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[OtpEmailViewModelsdh::class.java]
        binding.sendOtp.doOnTextChanged { text, start, before, count ->
            if (text?.length == 4){
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    viewModel.checkOtp("").observe(this@OtpEmailSudah, Observer { result ->
                        when (result) {
                            is Result.Success -> {
                                navigateToKonfrek()
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
        }

    }
    private fun navigateToKonfrek() {
        val intent = Intent(this, KonfirmasiRekSudah::class.java)
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
                binding.tvTimerOTP.text = time
            }

            override fun onFinish() {
                binding.tvTimerOTP.text = "00:00"
            }
        }
        timer.start()
    }
}