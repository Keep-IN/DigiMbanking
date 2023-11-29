package com.core.data.response.auth.createRekening.password


import com.google.gson.annotations.SerializedName

data class PasswordModel(
    @SerializedName("password")
    val password: String
)