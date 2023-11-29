package com.core.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.Profile.UbahPw.UbahPwRequest
import com.core.data.response.Profile.UbahPw.UbahPwResponse

import com.core.data.response.login.LoginRequest
import com.core.di.ApiContractLogin
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UbahPwRepository @Inject constructor(
    private val apiService: ApiContractLogin,
    private val userPreferences: UserPreferences,
    private val sharedPreferences: SharedPreferences
) {
    fun ubahPw (
        token: String,
        pwLama: String,
        pwBaru: String,
        konfirmPw: String
    ): LiveData<Result<UbahPwResponse>> = liveData {
     emit(Result.Loading)
        val response = apiService.ubahPw("Bearer $token", UbahPwRequest(pwLama, pwBaru, konfirmPw))
        val responseBody = response.body() ?: UbahPwResponse("", 0)
     try {
         if(response.isSuccessful) {
             emit(Result.Success(responseBody))
         } else {
             val errorBody = response.errorBody()?.string()
             val errorMessage = try {
                 JSONObject(errorBody).getString("")
             } catch (e: JSONException){
                 "Unknown Error Occured"
             }
             emit(Result.Error(errorMessage))
         }
     } catch (e: Exception) {
         emit(Result.Error(e.message ?: "An error occured"))
     }
    }
}