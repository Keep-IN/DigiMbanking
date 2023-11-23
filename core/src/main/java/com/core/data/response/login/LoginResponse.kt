package com.core.data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLoginResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)