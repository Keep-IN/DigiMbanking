package com.core.data.response.auth.createRekening.email


import com.google.gson.annotations.SerializedName

data class OtpResponse(
    @SerializedName("data")
    val `data`: DataOtp,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)