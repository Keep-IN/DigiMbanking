package com.core.data.response.auth.createRekening.otp


import com.google.gson.annotations.SerializedName

data class VerifOtpModel(
    @SerializedName("otp")
    val otp: String
)