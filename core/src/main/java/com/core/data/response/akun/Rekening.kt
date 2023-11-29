package com.core.data.response.akun


import com.google.gson.annotations.SerializedName

data class Rekening(
    @SerializedName("rekening")
    val noRekening: String,
    @SerializedName("saldo")
    val saldo: Double,
    @SerializedName("typeRekening")
    val tipeRekening: TipeRekening
)