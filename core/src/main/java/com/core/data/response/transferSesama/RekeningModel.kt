package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class RekeningModel(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nama_bank")
    val namaBank: String,
    @SerializedName("no_rekening")
    val noRekening: String
)