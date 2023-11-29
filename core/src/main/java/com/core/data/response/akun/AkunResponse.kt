package com.core.data.response.akun


import com.google.gson.annotations.SerializedName

data class AkunResponse(
    @SerializedName("data")
    val `data`: DataRekeningAkun,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)