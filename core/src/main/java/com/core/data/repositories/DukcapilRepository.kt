package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.dukcapil.DukcapilModel
import com.core.data.response.auth.createRekening.dukcapil.DukcapilResponse
import com.core.di.ApiContractDukcapil
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DukcapilRepository @Inject constructor(
    private val apiservice: ApiContractDukcapil
){
    fun postDukcapil (
        nik : String
    ) : LiveData<Result<DukcapilResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiservice.postDukcapil(DukcapilModel(nik))
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