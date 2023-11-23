package com.core.data.response.listbank


import com.google.gson.annotations.SerializedName

data class ListBankResponse(
    @SerializedName("data")
    val data: List<DataBank>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)