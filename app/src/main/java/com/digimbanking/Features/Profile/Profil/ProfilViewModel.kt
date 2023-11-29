package com.digimbanking.Features.Profile.Profil

import androidx.lifecycle.ViewModel
import com.core.data.repositories.ProfilRepository
import com.core.data.repositories.UbahPwRepository
import com.core.domain.model.DataLogin
import com.core.domain.model.DataProfil
import com.core.domain.model.LoginModel
import com.core.domain.model.ProfilModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(
    private val profilRepository: ProfilRepository
): ViewModel(){
    fun getProfil() = profilRepository.getProfil()
}