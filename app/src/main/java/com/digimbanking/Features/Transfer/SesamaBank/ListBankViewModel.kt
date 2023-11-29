package com.digimbanking.Features.Transfer.SesamaBank

import androidx.lifecycle.ViewModel
import com.core.data.repositories.TransferRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListBankViewModel @Inject constructor(
    private val transferRepository: TransferRepository
): ViewModel() {
    fun getListBank() = transferRepository.getListBank()
}