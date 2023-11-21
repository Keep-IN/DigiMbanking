package com.core.data.response.auth.createRekening.pilihKartu


import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("data")
    val `data`: List<DataCard>
)