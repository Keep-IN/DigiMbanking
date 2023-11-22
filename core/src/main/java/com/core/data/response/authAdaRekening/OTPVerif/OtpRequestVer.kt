package com.core.data.response.authAdaRekening.OTPVerif

import com.google.gson.annotations.SerializedName

data class OtpRequestVer(

	@field:SerializedName("otp")
	val otp: String
)
