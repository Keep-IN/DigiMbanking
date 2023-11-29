package com.core.data.response.Nasabah

import com.google.gson.annotations.SerializedName

data class TipeRekening(
    @SerializedName("idTipe")
    val idTipe: Int,
    @SerializedName("limitTransfer")
    val limitTransfer: String,
    @SerializedName("namaTipe")
    val namaTipe: String
)