package com.core.di

import com.core.data.response.auth.createRekening.dukcapil.DukcapilModel
import com.core.data.response.auth.createRekening.dukcapil.DukcapilResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiContractDukcapil {
    @POST("users/dukcapil/ktp/validate")
    suspend fun postDukcapil (
        @Body request: DukcapilModel
    ) : Response<DukcapilResponse>
}