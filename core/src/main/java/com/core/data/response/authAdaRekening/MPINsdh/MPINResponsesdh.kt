package com.core.data.response.authAdaRekening.MPINsdh

import com.google.gson.annotations.SerializedName

data class MPINResponsesdh(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
