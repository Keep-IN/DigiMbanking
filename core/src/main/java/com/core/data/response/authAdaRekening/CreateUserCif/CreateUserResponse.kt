package com.core.data.response.authAdaRekening.CreateUserCif

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)
