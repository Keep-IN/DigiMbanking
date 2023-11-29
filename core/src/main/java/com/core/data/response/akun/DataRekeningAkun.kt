package com.core.data.response.akun


import com.google.gson.annotations.SerializedName

data class DataRekeningAkun(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("accounts")
    val rekening: List<Rekening>
)