package com.core.data.response.authAdaRekening.KonfirmasiRekening

import com.google.gson.annotations.SerializedName

data class RekeningResponse(

	@field:SerializedName("data")
	val data: DataRekeningResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)