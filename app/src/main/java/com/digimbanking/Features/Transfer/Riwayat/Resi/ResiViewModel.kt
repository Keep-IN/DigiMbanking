package com.digimbanking.Features.Transfer.Riwayat.Resi

import androidx.lifecycle.ViewModel
import com.core.data.repositories.ResiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResiViewModel @Inject constructor(
    private val resiRepository: ResiRepository
): ViewModel() {

    fun doResi(id: Int) = resiRepository.getResi(id)
}