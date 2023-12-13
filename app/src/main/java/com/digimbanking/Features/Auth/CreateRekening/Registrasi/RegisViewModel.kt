package com.digimbanking.Features.Auth.CreateRekening.Registrasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.repositories.OtpRepository
import com.core.data.repositories.PasswordRepository
import com.core.domain.model.DataNik
import com.core.domain.model.DataOtp
import com.core.domain.model.NikModel
import com.core.domain.model.OtpModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisViewModel  @Inject constructor(
    private val otpRepository: OtpRepository,
    private val passwordRepository: PasswordRepository
) : ViewModel(){
    fun doOtp(
        email: String
    ) = otpRepository.postOtp(email)

    fun checkOtp(
        otp: String
    ) = otpRepository.putOtp(otp)

    fun regenOtp(

    ) = otpRepository.regenerateOtp()

    var isEmailValid = false
    var isPasswordValid = false

    fun putPass(
        password: String
    ) = passwordRepository.putPassword(password)


    fun validateEmail(email: String): Boolean{
        isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
        return isEmailValid
    }

    fun validatePassword(password: String): Boolean{
        isPasswordValid = password.contains("(?=.*[0-9])(?=.*[a-zA-Z])".toRegex())
        return  isPasswordValid
    }

    fun validatePw(password: String): Boolean {
        return password.length >= 8
    }


}