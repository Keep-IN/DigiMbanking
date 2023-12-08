package com.core.data.response.authAdaRekening.CreateUserCif

import com.google.gson.annotations.SerializedName

data class CreateuserRequest(
    @field:SerializedName("noRekening")
    val noRekening: String
)
