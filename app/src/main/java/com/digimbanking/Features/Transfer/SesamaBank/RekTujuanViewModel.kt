package com.digimbanking.Features.Transfer.SesamaBank

import androidx.lifecycle.ViewModel

class RekTujuanViewModel: ViewModel() {
    var rekeningValid = false
    fun validateRekening(rekening: String): Boolean {
        rekeningValid = rekening.length > 16
        return rekeningValid
    }
}