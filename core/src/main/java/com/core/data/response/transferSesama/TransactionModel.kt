package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class TransactionModel(
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("mpin")
    val mpin: String,
    @SerializedName("noRekeningSumber")
    val noRekeningSumber: String,
    @SerializedName("noRekeningTujuan")
    val noRekeningTujuan: String,
    @SerializedName("nominal")
    val nominal: Int
)