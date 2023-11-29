package com.core.data.response.auth.createRekening.cif


import com.google.gson.annotations.SerializedName

data class CifModel(
    @SerializedName("alamat")
    val nik: String,
    @SerializedName("namaLengkap")
    val namaLengkap: String,
    @SerializedName("nik")
    val alamat: String,
    @SerializedName("pekerjaan")
    val pekerjaan: String,
    @SerializedName("penghasilan")
    val penghasilan: String
)