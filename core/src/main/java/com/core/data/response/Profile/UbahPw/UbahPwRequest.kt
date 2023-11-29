package com.core.data.response.Profile.UbahPw

import com.google.gson.annotations.SerializedName

data class UbahPwRequest(

	@field:SerializedName("oldPassword")
	val oldPassword: String,

	@field:SerializedName("newPassword")
	val newPassword: String,

	@field:SerializedName("confirmPassword")
	val confirmPassword: String
)
