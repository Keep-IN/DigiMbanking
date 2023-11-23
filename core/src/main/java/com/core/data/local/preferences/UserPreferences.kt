package com.core.data.local.preferences

import com.core.data.response.authAdaRekening.OTPsdh.DataOtpResponse

interface UserPreferences {

    suspend fun getIdd(): Int

    suspend fun setId(id : Int)
}