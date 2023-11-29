package com.core.data.response.auth.createRekening.mpin


import com.google.gson.annotations.SerializedName

data class MpinModel(
    @SerializedName("mpin")
    val mpin: String
)