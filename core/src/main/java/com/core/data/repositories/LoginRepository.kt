package com.core.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.login.DataLoginResponse
import com.core.data.response.login.LoginRequest
import com.core.data.response.login.LoginResponse
import com.core.di.ApiContractLogin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val apiService: ApiContractLogin,
    private val userPreferences: UserPreferences
) {
    fun login(
        email: String,
        password: String
    ): LiveData<Result<DataLoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(LoginRequest(password,email))
            userPreferences.storeToken(response.token)
            userPreferences.login(response)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("login", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }
}