package com.core.di


import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiRequest
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiResponse
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningRequest
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningResponse
import com.core.data.response.authAdaRekening.MPINsdh.MPINRequestsdh
import com.core.data.response.authAdaRekening.MPINsdh.MPINResponsesdh
import com.core.data.response.authAdaRekening.OTPsdh.OtpRequest
import com.core.data.response.authAdaRekening.OTPsdh.OtpResponse
import com.core.data.response.transferSesama.Data
import com.core.data.response.transferSesama.TransactionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiContract {
    @POST("accounts/transactions/{id}")
    fun postTransaksi(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body data: Data
    ): Call<TransactionResponse>

}