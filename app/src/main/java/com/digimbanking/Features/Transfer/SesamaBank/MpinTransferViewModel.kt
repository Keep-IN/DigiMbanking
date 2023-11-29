package com.digimbanking.Features.Transfer.SesamaBank

import androidx.lifecycle.ViewModel
import com.core.data.repositories.TransferRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MpinTransferViewModel @Inject constructor(
    private val transferRepository: TransferRepository
): ViewModel(){
    fun doTransaction(
        mpin: String,
        catatan: String,
        noRekSumber: String,
        noRekTujuan: String,
        nominal: Int
    ) = transferRepository.postTransaction(mpin, catatan,noRekSumber ,noRekTujuan, nominal)
}