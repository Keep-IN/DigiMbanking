package com.core.di

import com.core.data.response.Profile.Profile.DataProfileResponse
import com.core.data.response.Profile.Profile.ProfileResponse
import com.core.data.response.login.DataLoginResponse
import com.core.data.response.login.LoginRequest
import com.core.data.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContractLogin {
    @POST("api/users/login")
    suspend fun login(
        @Body response: LoginRequest
    ): DataLoginResponse

}