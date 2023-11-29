package com.core.data.response.authAdaRekening.OTPsdh

import com.google.gson.annotations.SerializedName

data class OtpRequest(

	@field:SerializedName("email")
	val email: String
)
