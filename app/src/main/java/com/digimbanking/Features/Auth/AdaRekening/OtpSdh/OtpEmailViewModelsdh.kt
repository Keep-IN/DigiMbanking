package com.digimbanking.Features.Auth.AdaRekening.OtpSdh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.repositories.EmailRepositorySdh
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpEmailViewModelsdh @Inject constructor(
    private val otpSent : EmailRepositorySdh,
) :ViewModel(){

    fun otpSent(
        email: String
    ) = otpSent.postOtp(email)

    fun checkOtp(
        otp: String
    ) = otpSent.putOtp(otp)


    val otpCodeLiveData = MutableLiveData<String>()


    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(email)
    }
}