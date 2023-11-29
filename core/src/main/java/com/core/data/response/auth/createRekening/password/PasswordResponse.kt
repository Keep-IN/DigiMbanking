package com.core.data.response.auth.createRekening.password


import com.google.gson.annotations.SerializedName

data class PasswordResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)