package com.core.data.response.Profile.Profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("data")
	val data: DataProfileResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class RekeningItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("tipeRekening")
	val tipeRekening: TipeRekening,

	@field:SerializedName("noRekening")
	val noRekening: Long,

	@field:SerializedName("saldo")
	val saldo: Any
)

data class TipeRekening(

	@field:SerializedName("idTipe")
	val idTipe: Int,

	@field:SerializedName("namaTipe")
	val namaTipe: String,

	@field:SerializedName("limitTransfer")
	val limitTransfer: String
)