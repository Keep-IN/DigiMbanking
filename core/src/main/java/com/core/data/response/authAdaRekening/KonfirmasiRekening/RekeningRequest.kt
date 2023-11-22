package com.core.data.response.authAdaRekening.KonfirmasiRekening

import com.google.gson.annotations.SerializedName

data class RekeningRequest(

	@field:SerializedName("noRekening")
	val noRekening: Long
)
