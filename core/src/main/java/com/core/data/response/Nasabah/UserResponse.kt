package com.core.data.response.Nasabah

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val `data`: DataUser,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)