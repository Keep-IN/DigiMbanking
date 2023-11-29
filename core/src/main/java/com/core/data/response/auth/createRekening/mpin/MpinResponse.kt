package com.core.data.response.auth.createRekening.mpin


import com.google.gson.annotations.SerializedName

data class MpinResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)