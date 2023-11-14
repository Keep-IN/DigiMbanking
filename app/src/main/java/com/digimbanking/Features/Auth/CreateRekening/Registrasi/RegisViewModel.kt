package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.domain.model.DataNik
import com.core.domain.model.DataOtp
import com.core.domain.model.NikModel
import com.core.domain.model.OtpModel

class RegisViewModel : ViewModel() {
    var isEmailValid = false
    var isPasswordValid = false


    val otpCodeLiveData = MutableLiveData<String>()

    fun validateEmail(email: String): Boolean{
        isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
        return isEmailValid
    }

    fun validatePassword(password: String): Boolean{
        isPasswordValid = password.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return  isPasswordValid
    }

    fun generateRandomOtp() {
        val randomOtp = (1000..9999).random()
        otpCodeLiveData.value = randomOtp.toString()
    }
}