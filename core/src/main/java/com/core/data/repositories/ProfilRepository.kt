package com.core.data.repositories

import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.local.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.Nasabah.DataUser
import com.core.data.response.Nasabah.UserResponse
import com.core.data.response.Profile.Profile.DataProfilResponse
import com.core.data.response.Profile.Profile.ProfilResponse

import com.core.di.ApiContractLogin
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Executable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilRepository @Inject constructor(
    private val apiService: ApiContractLogin,
) {
    fun getProfil(): LiveData<Result<ProfilResponse>> =liveData {
        emit(Result.Loading)
        try {
            val response = apiService.profile()
            val responseBody = response.body() ?: ProfilResponse(DataProfilResponse("", listOf(), "",""), "", 0)
            if(response.isSuccessful){
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
        } catch (e: Exception){
            emit(Result.Error(e.message ?: "An error occured"))
        }
    }
}