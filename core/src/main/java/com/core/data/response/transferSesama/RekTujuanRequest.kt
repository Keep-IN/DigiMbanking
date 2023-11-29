package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class RekTujuanRequest(
    @SerializedName("noRekening")
    val noRekening: String
)