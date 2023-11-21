package com.core.di

import com.core.data.response.transferSesama.DataTransaksi
import com.core.data.response.transferSesama.TransactionModel
import com.core.data.response.transferSesama.TransactionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContractTransfer {

    @POST("transfer/digibank")
    suspend fun postTransaksi(
        @Body request: TransactionModel
    ): Response<TransactionResponse>
}