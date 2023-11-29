package com.core.data.response.transferSesama


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionResponse(
    @SerializedName("data")
    val dataTransaksi: DataTransaksi,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("penerima")
    val penerima: RekeningModel,
    @SerializedName("pengirim")
    val pengirim: RekeningModel
): Parcelable