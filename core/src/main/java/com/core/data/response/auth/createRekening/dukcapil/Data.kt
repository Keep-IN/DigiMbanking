package com.core.data.response.auth.createRekening.dukcapil


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("pekerjaan")
    val pekerjaan: String
) : Parcelable