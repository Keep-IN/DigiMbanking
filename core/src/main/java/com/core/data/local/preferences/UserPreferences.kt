package com.core.data.local.preferences

import com.core.data.response.authAdaRekening.OTPsdh.DataOtpResponse

interface UserPreferences {

    suspend fun getIdotp(response: DataOtpResponse)

    suspend fun getIResponse(token : Int)
}