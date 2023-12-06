package com.digimbanking.Features.Transfer.SesamaBank

import androidx.lifecycle.ViewModel
import com.core.data.repositories.AkunRepository
import com.core.data.repositories.ProfilRepository
import com.core.data.repositories.RiwayatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputNominalViewModel @Inject constructor(
    private val apiService: AkunRepository
): ViewModel() {
    fun getUser() = apiService.getAkun()
}