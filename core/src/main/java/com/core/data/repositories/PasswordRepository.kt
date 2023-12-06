package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.lokal.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.password.PasswordModel
import com.core.data.response.auth.createRekening.password.PasswordResponse
import com.core.di.ApiContractCreateRekening
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordRepository @Inject constructor(
    private val apiService : ApiContractCreateRekening,
    private val userPreferences: UserPreferences
){
    fun putPassword (
        password : String
    ) : LiveData<Result<PasswordResponse>> = liveData {
        emit(Result.Loading)

        try {
            val id = userPreferences.getUser()
            val response = apiService.createPassword(id, PasswordModel(password))
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMassage = try {
                    JSONObject(errorBody).getString("massage")
                } catch (e : JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMassage))
            }
        } catch (e : Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}