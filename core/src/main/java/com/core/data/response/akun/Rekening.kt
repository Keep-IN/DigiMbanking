package com.core.data.response.akun


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rekening(
    @SerializedName("rekening")
    val noRekening: String,
    @SerializedName("saldo")
    val saldo: Double,
    @SerializedName("typeRekening")
    val tipeRekening: TipeRekening
): Parcelable