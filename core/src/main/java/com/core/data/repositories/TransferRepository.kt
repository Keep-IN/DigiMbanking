package com.core.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.response.listbank.ListBankResponse
import com.core.data.response.transferSesama.DataNasabahTujuan
import com.core.data.response.transferSesama.NasabahTujuanResponse
import com.core.data.response.transferSesama.RekTujuanRequest
import com.core.data.response.transferSesama.TransactionModel
import com.core.data.response.transferSesama.TransactionResponse
import com.core.di.ApiContractTransfer
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
                if (responseBody != null) {
                    emit(Result.Error(responseBody.message))
                }
            }
        } catch (e: Exception){
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun getListBank(): LiveData<Result<ListBankResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getBanks()
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            } else {
                emit(Result.Error("Failed to get a valid response"))
            }
        } catch (e: Exception){
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun postRekeningTujuan(rekening: String): LiveData<Result<NasabahTujuanResponse>> = liveData {
        emit(Result.Loading)
        val response = apiService.postRekTujuan(RekTujuanRequest(rekening))
        val responseBody = response.body()
        try{
            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            } else {
                if (responseBody != null) {
                    emit(Result.Error(responseBody.message))
                }
            }
        } catch (e: Exception){
            if (responseBody != null) {
                emit(Result.Error(responseBody.message))
            }
//            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}