package com.core.data.response.auth.createRekening.pilihKartu


import com.google.gson.annotations.SerializedName

data class DataCard(
    @SerializedName("idTipe")
    val idTipe: Int,
    @SerializedName("limitTransfer")
    val limitTransfer: String,
    @SerializedName("namaTipe")
    val namaTipe: String
)