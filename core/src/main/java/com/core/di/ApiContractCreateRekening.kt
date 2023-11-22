package com.core.di

import com.core.data.response.auth.createRekening.email.OtpModel
import com.core.data.response.auth.createRekening.email.OtpResponse
import com.core.data.response.auth.createRekening.pilihKartu.CardResponse
import com.core.data.response.auth.createRekening.pilihKartu.DataCard
import com.core.data.response.transferSesama.Data
import com.core.data.response.transferSesama.TransactionResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiContractCreateRekening {
    @GET("users/cards")
    suspend fun getTipeKartu (
    ): CardResponse

    @POST("users/otp-generate")
    suspend fun generateOtp (
        @Body request: OtpModel
    ) : Response<OtpResponse>
}