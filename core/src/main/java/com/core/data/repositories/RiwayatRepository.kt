package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.core.data.network.Result
import com.core.data.response.riwayatTransaksi.RiwayatResponse
import com.core.data.response.riwayatTransaksi.Transaction
import com.core.di.ApiContractRiwayat
import com.core.domain.model.DataRiwayat
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RiwayatRepository @Inject constructor(
    private val apiService: ApiContractRiwayat
){
    fun getRiwayat(
        kredit: Boolean,
        debit: Boolean,
        dateStart: String,
        dateEnd: String
    ): LiveData<Result<RiwayatResponse>> = liveData {
        emit(Result.Loading)
        val response = apiService.getRiwayat(kredit, debit, dateStart, dateEnd)
        val responseBody = response.body() ?: RiwayatResponse(false, "", listOf())
        try{
            if (response.isSuccessful){
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
        }catch(e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }
}