package com.core.data.response.transferSesama


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RekeningModel(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaBank")
    val namaBank: String,
    @SerializedName("noRekening")
    val noRekening: String
): Parcelable