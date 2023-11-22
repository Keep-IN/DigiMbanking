package com.core.data.response.authAdaRekening.OTPsdh

import com.google.gson.annotations.SerializedName

data class OtpResponse(

	@field:SerializedName("data")
	val data: DataOtpResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

