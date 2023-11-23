package com.core.di

import com.core.data.response.Profile.Profile.DataProfileResponse
import com.core.data.response.Profile.Profile.ProfileResponse
import com.core.data.response.login.LoginRequest
import com.core.data.response.login.LoginResponse
import com.core.data.response.transferSesama.Data
import com.core.data.response.transferSesama.TransactionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContract {
    @POST("accounts/transactions/{id}")
    fun postTransaksi(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body data: Data
    ): Call<TransactionResponse>
}