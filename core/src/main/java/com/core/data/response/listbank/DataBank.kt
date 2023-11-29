package com.core.data.response.listbank


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataBank(
    @SerializedName("kodeBank")
    val kodeBank: Int,
    @SerializedName("namaBank")
    val namaBank: String
): Parcelable