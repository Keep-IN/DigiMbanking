package com.core.data.response.transferSesama


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataNasabahTujuan(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaBank")
    val namaBank: String,
    @SerializedName("noRekening")
    val noRekening: Long
): Parcelable