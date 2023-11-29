package com.core.data.response.Profile.UbahPw

import com.google.gson.annotations.SerializedName

data class UbahPwResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
