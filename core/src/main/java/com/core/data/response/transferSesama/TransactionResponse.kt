package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("data")
    val dataTransaksi: DataTransaksi,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("penerima")
    val penerima: RekeningModel,
    @SerializedName("pengirim")
    val rekeningModel: RekeningModel
)