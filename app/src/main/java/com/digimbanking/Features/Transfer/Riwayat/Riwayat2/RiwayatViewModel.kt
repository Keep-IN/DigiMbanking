package com.digimbanking.Features.Transfer.Riwayat.Riwayat2

import androidx.lifecycle.ViewModel
import com.core.data.repositories.RiwayatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RiwayatViewModel @Inject constructor(
    private val riwayatRepository: RiwayatRepository
): ViewModel() {
    fun doRiwayat(
        kredit: Boolean,
        debit: Boolean,
        dateStart: String,
        dateEnd: String
    ) = riwayatRepository.getRiwayat(kredit, debit, dateStart, dateEnd)
}