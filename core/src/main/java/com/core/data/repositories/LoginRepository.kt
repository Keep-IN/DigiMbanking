package com.core.data.repositories

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.login.DataLoginResponse
import com.core.data.response.login.LoginRequest
import com.core.data.response.login.LoginResponse
import com.core.di.ApiContractLogin
import com.core.domain.model.RiwayatGetResponse
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val apiService: ApiContractLogin

) {
    fun login(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        val response = apiService.login(LoginRequest(password, email))
        val responseBody = response.body() ?: LoginResponse(DataLoginResponse(""), "", 0)
        try {

            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}