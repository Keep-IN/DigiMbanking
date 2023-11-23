package com.core.data.local.preferences

import com.core.data.local.entity.User
import com.core.data.response.login.DataLoginResponse
import com.core.data.response.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    fun getUser(): Flow<User>
    suspend fun getToken(): String
    suspend fun login(response: DataLoginResponse)
    suspend fun logout()
    suspend fun storeToken(token: String)
    suspend fun sessionLogin(token: String)
}