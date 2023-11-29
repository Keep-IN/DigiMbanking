package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.akun.AkunResponse
import com.core.data.response.akun.DataRekeningAkun
import com.core.di.ApiContractRiwayat
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AkunRepository @Inject constructor(
    private val apiService: ApiContractRiwayat
){
    fun getAkun(): LiveData<Result<AkunResponse>> = liveData {
        emit(Result.Loading)
        val response = apiService.getAccountUser()
        val responseBody = response.body()
        try{
            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            }else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = try{
                    JSONObject(errorBody).getString("message")
                }catch (e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        }catch (e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }
}