package com.core.data.response.auth.createRekening.otp


import com.google.gson.annotations.SerializedName

data class VerificationOtpResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)