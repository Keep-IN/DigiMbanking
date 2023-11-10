package com.digimbanking.Features.Auth.AdaRekening.OtpSdh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtpEmailViewModelsdh: ViewModel() {
    val otpCodeLiveData = MutableLiveData<String>()

    fun generateRandomOtp() {
        val randomOtp = (1000..9999).random()
        otpCodeLiveData.value = randomOtp.toString()
    }
}