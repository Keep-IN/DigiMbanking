package com.core.di

import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiRequest
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiResponse
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningRequest
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningResponse
import com.core.data.response.authAdaRekening.MPINsdh.MPINRequestsdh
import com.core.data.response.authAdaRekening.MPINsdh.MPINResponsesdh
import com.core.data.response.authAdaRekening.OTPVerif.OtpRequestVer
import com.core.data.response.authAdaRekening.OTPVerif.OtpVerResponse
import com.core.data.response.authAdaRekening.OTPsdh.DataOtpResponse
import com.core.data.response.authAdaRekening.OTPsdh.OtpRequest
import com.core.data.response.authAdaRekening.OTPsdh.OtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiContractAdaRekening {

    @POST("/api/v1/users/otp-generate")
    suspend fun sendOtpGenerate(
        @Body request: OtpRequest
    ): Response<OtpResponse>

    @PUT("/api/v1/users/{id}/otp-verification")
    suspend fun verOtp(
        @Path("id") id: Int,
        @Body data: OtpRequestVer
    ): Response<OtpVerResponse>


    @POST("/api/v1/users/confirm-accounts")
    suspend fun confirmRekening(
        @Body data: RekeningRequest
    ): Response <RekeningResponse>

    @PUT("/api/v1/users/{id}/password")
    suspend fun createPassword(
        @Path ("id") id: Int,
        @Body data: KataSandiRequest
    ):  Response <KataSandiResponse>

    @PUT("v1/users/{id}/mpin")
    suspend fun createMPIN(
        @Body data: MPINRequestsdh
    ): Response <MPINResponsesdh>
}