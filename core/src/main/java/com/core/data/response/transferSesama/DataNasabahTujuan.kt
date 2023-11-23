package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class DataNasabahTujuan(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaBank")
    val namaBank: String,
    @SerializedName("noRekening")
    val noRekening: Long
)