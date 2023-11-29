package com.core.data.response.auth.createRekening.email


import com.google.gson.annotations.SerializedName

data class DataOtp(
    @SerializedName("email")
    val email: String,
    @SerializedName("idUser")
    val idUser: Int
)