package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.domain.model.Rekening

class KonfRekViewModelsdh: ViewModel() {
    private val repository = RekeningRepository()
    val rekeningLiveData = MutableLiveData<Rekening?>()

    fun cekNomorRekening(nomorRekening: String) {
        val rekening = repository.getRekeningByNomor(nomorRekening)
        rekeningLiveData.value = rekening
    }
}