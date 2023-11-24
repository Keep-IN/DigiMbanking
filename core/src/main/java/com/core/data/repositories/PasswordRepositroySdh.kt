package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiRequest
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiResponse
import com.core.data.response.authAdaRekening.OTPVerif.OtpRequestVer
import com.core.data.response.authAdaRekening.OTPVerif.OtpVerResponse
import com.core.di.ApiContractAdaRekening
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordRepositroySdh @Inject constructor(
    private val apiService : ApiContractAdaRekening,
    private val userPreferences: UserPreferences,
){
    fun putNewpass(
        password: String
    ): LiveData<Result<KataSandiResponse>> = liveData {
        emit(Result.Loading)

        try {
            val id = userPreferences.getIdd()
            val response = apiService.createPassword(id, KataSandiRequest(password))
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