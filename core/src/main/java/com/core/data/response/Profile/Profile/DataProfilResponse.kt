package com.core.data.response.Profile.Profile

import com.google.gson.annotations.SerializedName

data class DataProfilResponse(

@field:SerializedName("nik")
val nik: String,

@field:SerializedName("accounts")
val rekening: List<RekeningItem>,

@field:SerializedName("name")
val name: String,

@field:SerializedName("email")
val email: String
)