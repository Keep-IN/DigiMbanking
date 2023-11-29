package com.digimbanking.Features.Akun

import androidx.lifecycle.ViewModel
import com.core.data.repositories.AkunRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AkunViewModel @Inject constructor(
    private val akunRepository: AkunRepository
): ViewModel() {
    fun doAkun() = akunRepository.getAkun()
}