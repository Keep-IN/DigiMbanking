package com.core.di

import com.core.data.response.transferSesama.Data
import com.core.data.response.transferSesama.TransactionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContract {
    @POST("accounts/transactions/{id}")
    fun postTrans(
        @Path("id") id: Int,
        @Body data: Data
    ): Call<TransactionResponse>
}