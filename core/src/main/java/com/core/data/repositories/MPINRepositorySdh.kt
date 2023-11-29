package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferencesSdh
import com.core.data.network.Result
import com.core.data.response.authAdaRekening.MPINsdh.MPINRequestsdh
import com.core.data.response.authAdaRekening.MPINsdh.MPINResponsesdh
import com.core.di.ApiContractAdaRekening
import javax.inject.Inject

class MPINRepositorySdh @Inject constructor(
    private val apiService : ApiContractAdaRekening,
    private val userPreferencesSdh: UserPreferencesSdh,
) {

    fun putMPIN(
        mpin: String
    ): LiveData<Result<MPINResponsesdh>> = liveData {
        emit(Result.Loading)

        try {
            val id = userPreferencesSdh.getIdd()
            val response = apiService.createMPIN(id, MPINRequestsdh(mpin))
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