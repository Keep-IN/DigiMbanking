package com.core.data.response.akun


import com.google.gson.annotations.SerializedName

data class TipeRekening(
    @SerializedName("idTipe")
    val idTipe: Int,
    @SerializedName("limitTransfer")
    val limitTransfer: String,
    @SerializedName("namaTipe")
    val namaTipe: String
)