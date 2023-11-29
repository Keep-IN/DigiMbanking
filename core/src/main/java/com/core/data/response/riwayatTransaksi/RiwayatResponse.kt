package com.core.data.response.riwayatTransaksi

data class RiwayatResponse(
    val error: Boolean,
    val message: String,
    val transactions: List<Transaction>
)