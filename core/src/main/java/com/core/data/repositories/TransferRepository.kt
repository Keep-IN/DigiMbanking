package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.listbank.ListBankResponse
import com.core.data.response.transferSesama.DataNasabahTujuan
import com.core.data.response.transferSesama.DataTransaksi
import com.core.data.response.transferSesama.NasabahTujuanResponse
import com.core.data.response.transferSesama.RekTujuanRequest
import com.core.data.response.transferSesama.RekeningModel
import com.core.data.response.transferSesama.TransactionModel
import com.core.data.response.transferSesama.TransactionResponse
import com.core.di.ApiContractTransfer
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransferRepository @Inject constructor(
    private val apiService: ApiContractTransfer
) {
    fun postTransaction(
        mpin: String,
        catatan: String,
        noRekSumber: String,
        noRekTujuan: String,
        nominal: Int
    ): LiveData<Result<TransactionResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postTransaksi(TransactionModel(catatan, mpin, noRekSumber, noRekTujuan, nominal))
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception){
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun getListBank(): LiveData<Result<ListBankResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getBanks()
            val responseBody = response.body() ?: ListBankResponse(listOf(),"", 0)
            if (response.isSuccessful){
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception){
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun postRekeningTujuan(rekeningSumber: String, rekeningTujuan: String): LiveData<Result<NasabahTujuanResponse>> = liveData {
        emit(Result.Loading)
        val response = apiService.postRekTujuan(RekTujuanRequest(rekeningSumber, rekeningTujuan))
        val responseBody = response.body() ?: NasabahTujuanResponse(DataNasabahTujuan("", "", 0L), "", 0)
        try{
            if(response.isSuccessful){
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception){
            emit(Result.Error(responseBody.message))
        }
    }
}