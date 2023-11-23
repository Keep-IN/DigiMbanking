package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.authAdaRekening.OTPVerif.OtpRequestVer
import com.core.data.response.authAdaRekening.OTPVerif.OtpVerResponse
import com.core.data.response.authAdaRekening.OTPsdh.DataOtpResponse
import com.core.data.response.authAdaRekening.OTPsdh.OtpRequest
import com.core.data.response.authAdaRekening.OTPsdh.OtpResponse
import com.core.di.ApiContractAdaRekening
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmailRepositorySdh @Inject constructor(
    private val apiService : ApiContractAdaRekening,
    private val userPreferences: UserPreferences,
){
    fun postOtp(
        email: String,
    ) : LiveData<Result<OtpResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.sendOtpGenerate(OtpRequest(email))
            val responseBody = response.body()
            if (responseBody != null) {
                userPreferences.setId(responseBody.data.idUser)
            }
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                emit(Result.Error("Failed to get a valid response"))
            }
        } catch (e : Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun putOtp(
        otp: String
    ): LiveData<Result<OtpVerResponse>> = liveData {
        emit(Result.Loading)

        try {
            val id = userPreferences.getIdd()
            val response = apiService.verOtp(id, OtpRequestVer(otp))
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                emit(Result.Error("Failed to get a valid response"))
            }
        } catch (e: Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

}