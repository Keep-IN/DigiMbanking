package com.core.data.response.akun


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipeRekening(
    @SerializedName("idTipe")
    val idTipe: Int,
    @SerializedName("limitTransfer")
    val limitTransfer: String,
    @SerializedName("namaTipe")
    val namaTipe: String
): Parcelable