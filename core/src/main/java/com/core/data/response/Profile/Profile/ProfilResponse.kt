package com.core.data.response.Profile.Profile

import com.google.gson.annotations.SerializedName

data class ProfilResponse(

	@field:SerializedName("data")
	val data: DataProfilResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class RekeningItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("typeRekening")
	val tipeRekening: TipeRekening,

	@field:SerializedName("rekening")
	val noRekening: String,

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
