package com.core.data.response.auth.createRekening.email


import com.google.gson.annotations.SerializedName

data class OtpModel(
    @SerializedName("email")
    val email: String
)