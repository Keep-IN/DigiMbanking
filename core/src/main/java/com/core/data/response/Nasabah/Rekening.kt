package com.core.data.response.Nasabah

import com.google.gson.annotations.SerializedName

data class Rekening(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("noRekening")
    val noRekening: Long,
    @SerializedName("saldo")
    val saldo: Double,
    @SerializedName("tipeRekening")
    val tipeRekening: TipeRekening
)