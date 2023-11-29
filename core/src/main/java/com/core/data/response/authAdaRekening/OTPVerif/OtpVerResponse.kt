package com.core.data.response.authAdaRekening.OTPVerif

import com.google.gson.annotations.SerializedName

data class OtpVerResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
