package com.digimbanking.Features.Transfer.Riwayat.Mutasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.core.data.repositories.RiwayatRepository
import com.core.data.response.riwayatTransaksi.RiwayatResponse
import com.core.domain.model.RiwayatItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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