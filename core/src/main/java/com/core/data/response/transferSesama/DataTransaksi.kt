package com.core.data.response.transferSesama


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTransaksi(
    @SerializedName("biayaAdmin")
    val biayaAdmin: Double,
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jenisTransaksi")
    val jenisTransaksi: String,
    @SerializedName("timeTransaksi")
    val timeTransaksi: String,
    @SerializedName("totalTransaksi")
    val totalTransaksi: String
): Parcelable