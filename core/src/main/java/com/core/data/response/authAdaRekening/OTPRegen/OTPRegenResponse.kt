package com.core.data.response.authAdaRekening.OTPRegen

import com.google.gson.annotations.SerializedName

data class OTPRegenResponse(

	@field:SerializedName("otp")
	val otp: String,

	@field:SerializedName("status")
	val status: Int
)
