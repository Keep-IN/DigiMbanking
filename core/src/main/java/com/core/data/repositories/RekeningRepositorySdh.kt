package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningRequest
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningResponse
import com.core.di.ApiContractAdaRekening
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RekeningRepositorySdh @Inject constructor(
    private val apiService: ApiContractAdaRekening,

) {

    fun checkRekening(
        noRekening: String,
    ): LiveData<Result<RekeningResponse>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.confirmRekening(RekeningRequest(noRekening))
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
