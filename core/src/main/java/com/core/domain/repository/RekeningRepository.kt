package com.core.domain.repository

import com.core.domain.model.Rekening

class RekeningRepository {
    private val mockData = listOf(
        Rekening("1234567890123512", "John Doe", "J"),
        Rekening("0987654321124567", "Alek","A"),
        Rekening("8490264756184905","Bunda","B")

    )

    fun getRekeningByNomor(nomorRekening: String): Rekening? {
        return mockData.find { it.nomorRekening == nomorRekening }
    }
}