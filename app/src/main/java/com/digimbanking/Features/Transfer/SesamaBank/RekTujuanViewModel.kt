package com.digimbanking.Features.Transfer.SesamaBank

import android.util.Log
import androidx.lifecycle.ViewModel
import com.core.data.repositories.AkunRepository
import com.core.data.repositories.TransferRepository
import com.core.domain.model.DataNasabah
import com.core.domain.model.NasabahModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RekTujuanViewModel @Inject constructor(
    private val transferRepository: TransferRepository,
    private val akunRepository: AkunRepository
): ViewModel() {
    var rekeningValid = false
    fun validateRekening(rekening: String): Boolean {
        rekeningValid = rekening.length > 9
        return rekeningValid
    }

    fun validateCredential(bank: String, rekening: String): NasabahModel? {
        var dataNasabah: NasabahModel? = null
        DataNasabah.listNasabah.forEach {
            if (it.bank.lowercase() == bank.lowercase() && it.rekening == rekening){
                dataNasabah = it
                return@forEach
            }
        }
        return dataNasabah
    }

    fun postRekening(rekeningSumber: String, rekeningTujuan: String) = transferRepository.postRekeningTujuan(rekeningSumber, rekeningTujuan)

    fun getUser() = akunRepository.getAkun()
}