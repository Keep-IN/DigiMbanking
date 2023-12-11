package com.digimbanking.Features.Transfer.SesamaBank

import androidx.lifecycle.ViewModel
import com.core.data.repositories.AkunRepository
import com.core.data.repositories.ProfilRepository
import com.core.data.repositories.RiwayatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.BigInteger
import javax.inject.Inject

@HiltViewModel
class InputNominalViewModel @Inject constructor(
    private val apiService: AkunRepository
): ViewModel() {
    private var isNominalValid = false

    fun getUser() = apiService.getAkun()
    fun validateNoninal(nominal: String): Boolean{
        isNominalValid = nominal.toBigInteger() >= BigInteger.valueOf(10000)
        return isNominalValid
    }
}