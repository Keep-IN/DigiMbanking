package com.core.data.local.preferences

import com.core.data.local.entity.User
import com.core.data.response.authAdaRekening.OTPsdh.DataOtpResponse
import kotlinx.coroutines.flow.Flow

interface UserPreferences {

    suspend fun getUser(): Int

    suspend fun getIdotp(response: DataOtpResponse)

    suspend fun getIResponse(token : Int)
}