package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class RekTujuanRequest(
    @SerializedName("noRekeningSumber")
    val noRekeningSumber: String,
    @SerializedName("noRekeningTujuan")
    val noRekeningTujuan: String
)