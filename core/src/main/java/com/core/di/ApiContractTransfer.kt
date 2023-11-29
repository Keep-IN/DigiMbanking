package com.core.di

import com.core.data.response.listbank.ListBankResponse
import com.core.data.response.transferSesama.NasabahTujuanResponse
import com.core.data.response.transferSesama.RekTujuanRequest
import com.core.data.response.transferSesama.TransactionModel
import com.core.data.response.transferSesama.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiContractTransfer {

    @POST("transfer/digibank")
    suspend fun postTransaksi(
        @Body request: TransactionModel
    ): Response<TransactionResponse>

    @GET("transfer/banks")
    suspend fun getBanks(): Response<ListBankResponse>

    @POST("transfer/accounts")
    suspend fun postRekTujuan(
        @Body request: RekTujuanRequest
    ): Response<NasabahTujuanResponse>
}