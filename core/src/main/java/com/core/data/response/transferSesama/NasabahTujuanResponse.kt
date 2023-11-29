package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class NasabahTujuanResponse(
    @SerializedName("data")
    val data: DataNasabahTujuan,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)