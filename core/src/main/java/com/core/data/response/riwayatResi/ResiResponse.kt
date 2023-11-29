package com.core.data.response.riwayatResi


import com.google.gson.annotations.SerializedName

data class ResiResponse(
    @SerializedName("data")
    val data: DataResi,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("penerima")
    val penerima: Penerima,
    @SerializedName("pengirim")
    val pengirim: Pengirim
)