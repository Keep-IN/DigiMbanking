package com.core.data.response.Profile.Profile

import com.google.gson.annotations.SerializedName

data class DataProfileResponse(
    @field:SerializedName("nik")
val nik: String,

    @field:SerializedName("rekening")
val rekening: List<RekeningItem>,

    @field:SerializedName("email")
val email: String
)
