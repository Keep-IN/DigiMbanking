package com.core.data.response.authAdaRekening.BuatKataSandisdh

import com.google.gson.annotations.SerializedName

data class KataSandiResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
