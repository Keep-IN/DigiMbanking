package com.core.di

import com.core.data.response.auth.createRekening.cif.CifModel
import com.core.data.response.auth.createRekening.cif.CifResponse
import com.core.data.response.auth.createRekening.email.OtpModel
import com.core.data.response.auth.createRekening.email.OtpResponse
import com.core.data.response.auth.createRekening.mpin.MpinModel
import com.core.data.response.auth.createRekening.mpin.MpinResponse
import com.core.data.response.auth.createRekening.otp.VerifOtpModel
import com.core.data.response.auth.createRekening.otp.VerificationOtpResponse
import com.core.data.response.auth.createRekening.password.PasswordModel
import com.core.data.response.auth.createRekening.password.PasswordResponse
import com.core.data.response.auth.createRekening.pilihKartu.CardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiContractCreateRekening {
    @GET("users/cards")
    suspend fun getTipeKartu (
    ): CardResponse

    @POST("users/otp-generate")
    suspend fun generateOtp (
        @Body request: OtpModel
    ) : Response<OtpResponse>

    @PUT("users/{id}/otp-verification")
    suspend fun verOtp(
        @Path("id") id: Int,
        @Body data: VerifOtpModel
    ): Response<VerificationOtpResponse>

    @POST("users/{userid}/tipe-rekening/{cardid}/cif")
    suspend fun postCif (
        @Path("userid") userid: Int,
        @Path("cardid") cardid: Int,
        @Body data: CifModel
    ) : Response<CifResponse>

    @PUT("users/{id}/password")
    suspend fun createPassword(
        @Path ("id") id: Int,
        @Body data: PasswordModel
    ):  Response <PasswordResponse>

    @PUT("users/{id}/mpin")
    suspend fun putMpin(
        @Path("id") id: Int,
        @Body data : MpinModel
    ) : Response<MpinResponse>
}