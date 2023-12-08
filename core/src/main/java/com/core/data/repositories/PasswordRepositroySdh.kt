package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferencesSdh
import com.core.data.network.Result
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiRequest
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiResponse
import com.core.di.ApiContractAdaRekening
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordRepositroySdh @Inject constructor(
    private val apiService : ApiContractAdaRekening,
    private val userPreferencesSdh: UserPreferencesSdh,
) {
    fun putNewpass(
        password: String
    ): LiveData<Result<KataSandiResponse>> = liveData {
        emit(Result.Loading)

        try {
            val id = userPreferencesSdh.getIdd()
            val response = apiService.createPassword(id, KataSandiRequest(password))
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occured"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}