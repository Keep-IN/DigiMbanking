package com.core.data.response.auth.createRekening.resendotp


import com.google.gson.annotations.SerializedName

data class RegenerateOtpresponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)