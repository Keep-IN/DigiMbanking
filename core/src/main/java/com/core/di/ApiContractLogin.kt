package com.core.di

import com.core.data.response.Nasabah.UserResponse
import com.core.data.response.Profile.Profile.DataProfilResponse
import com.core.data.response.Profile.Profile.ProfilResponse
import com.core.data.response.Profile.UbahPw.UbahPwRequest
import com.core.data.response.Profile.UbahPw.UbahPwResponse
import com.core.data.response.login.DataLoginResponse
import com.core.data.response.login.LoginRequest
import com.core.data.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiContractLogin {
    @POST("users/login")
    suspend fun login(
        @Body response: LoginRequest
    ): Response<LoginResponse>

    @GET("profiling/accounts")
    suspend fun profile(): Response<ProfilResponse>

    @PUT ("profiling/change-password")
    suspend fun ubahPw(
        @Body response: UbahPwRequest
    ): Response<UbahPwResponse>
}