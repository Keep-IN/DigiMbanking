package com.digimbanking.Features.Auth.CreateRekening.Cif

import androidx.lifecycle.ViewModel
import com.core.domain.model.PekerjaanItemModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.data.repositories.DukcapilRepository
import com.core.domain.model.DataNik
import com.core.domain.model.NikModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CifViewModel @Inject constructor(
    private val dukcapilRepository: DukcapilRepository
) :ViewModel() {

    var isNikValid = true
    fun doDukcapil(
        nik: String
    ) = dukcapilRepository.postDukcapil(nik)

    fun validateNik(nik : String) : Boolean{
        isNikValid = nik.length > 5
        return isNikValid
    }
}