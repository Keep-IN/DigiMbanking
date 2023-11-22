package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.email.OtpModel
import com.core.data.response.auth.createRekening.email.OtpResponse
import com.core.di.ApiContractCreateRekening
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtpRepository @Inject constructor(
    private val apiService : ApiContractCreateRekening
) {
    fun postOtp(
        email: String,
    ) : LiveData<Result<OtpResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.generateOtp(OtpModel(email))
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