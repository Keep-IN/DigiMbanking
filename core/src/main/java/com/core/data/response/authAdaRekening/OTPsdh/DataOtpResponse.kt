package com.core.data.response.authAdaRekening.OTPsdh

import com.google.gson.annotations.SerializedName

data class DataOtpResponse(
    @field:SerializedName("idUser")
    val idUser: Int,

    @field:SerializedName("email")
    val email: String
)
