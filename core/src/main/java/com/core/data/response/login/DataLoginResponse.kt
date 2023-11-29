package com.core.data.response.login

import com.google.gson.annotations.SerializedName

data class DataLoginResponse(
    @field:SerializedName("token")
    val token: String
)