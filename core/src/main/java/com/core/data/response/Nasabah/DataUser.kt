package com.core.data.response.Nasabah

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("rekening")
    val rekening: List<Rekening>
)