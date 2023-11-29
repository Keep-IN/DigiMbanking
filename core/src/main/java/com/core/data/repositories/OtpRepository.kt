package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.lokal.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.email.OtpModel
import com.core.data.response.auth.createRekening.email.OtpResponse
import com.core.data.response.auth.createRekening.otp.VerifOtpModel
import com.core.data.response.auth.createRekening.otp.VerificationOtpResponse
import com.core.data.response.auth.createRekening.resendotp.RegenerateOtpresponse
import com.core.di.ApiContractCreateRekening
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtpRepository @Inject constructor(
    private val apiService : ApiContractCreateRekening,
    private val userPreferences: UserPreferences
) {
    fun postOtp(
        email: String,
    ) : LiveData<Result<OtpResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.generateOtp(OtpModel(email))
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
    ): LiveData<Result<VerificationOtpResponse>> = liveData {
        emit(Result.Loading)
        try {
            val id = userPreferences.getUser()
            val response = apiService.verOtp(id, VerifOtpModel(otp))
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

    fun regenerateOtp () : LiveData<Result<RegenerateOtpresponse>> = liveData {
        emit(Result.Loading)

        try {
            val id = userPreferences.getUser()
            val response = apiService.resendOtp(id)
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                emit(Result.Error("Failed to get a valid response"))
            }
        } catch (e : Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}