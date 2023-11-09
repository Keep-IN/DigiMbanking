package com.core.domain.model


data class RiwayatGetResponse(
    val status: Int,
    val riwayat: List<RiwayatItemModel> = mutableListOf()
)
