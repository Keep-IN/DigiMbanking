package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.riwayatResi.DataResi
import com.core.data.response.riwayatResi.Penerima
import com.core.data.response.riwayatResi.Pengirim
import com.core.data.response.riwayatResi.ResiResponse
import com.core.di.ApiContractRiwayat
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResiRepository @Inject constructor(
    private val apiService: ApiContractRiwayat
) {
    fun getResi(id: Int): LiveData<Result<ResiResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRiwayatById(id)
            val responseBody = response.body() ?: ResiResponse(
                DataResi(0, "",0,"",""), false,"",
                Penerima("","", ""),
                Pengirim("","","")
            )
            if (response.isSuccessful){
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException){
                    "Unknwon Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        } catch(e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }
}