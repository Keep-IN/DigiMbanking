package com.core.data.local.preferences

import com.core.data.response.login.DataLoginResponse

interface UserPreferences {
    //login auth
    suspend fun getToken(): String
    suspend fun login(response: DataLoginResponse)
    suspend fun logout()
    suspend fun storeToken(token: String)
    suspend fun sessionLogin(token: String)
}