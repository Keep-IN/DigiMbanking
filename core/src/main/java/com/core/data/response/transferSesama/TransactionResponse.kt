package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("penerima")
    val penerima: Penerima,
    @SerializedName("pengirim")
    val pengirim: Pengirim
)