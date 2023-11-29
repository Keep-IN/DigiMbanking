package com.core.di

import com.core.data.response.akun.AkunResponse
import com.core.data.response.riwayatResi.ResiResponse
import com.core.data.response.riwayatTransaksi.RiwayatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiContractRiwayat {
    @GET("accounts/transactions")
    suspend fun getRiwayat(
        @Query("isDebit") debit: Boolean,
        @Query("isKredit") kredit: Boolean,
        @Query("tanggalMulai") dateStart: String,
        @Query("tanggalAkhir") dateEnd: String
    ): Response<RiwayatResponse>

    @GET("accounts/transactions/{id}")
    suspend fun getRiwayatById(
        @Path("id") id: Int,
    ): Response<ResiResponse>

    @GET("profiling/accounts")
    suspend fun getAccountUser(): Response<AkunResponse>
}