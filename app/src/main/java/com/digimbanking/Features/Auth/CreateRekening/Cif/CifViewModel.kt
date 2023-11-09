package com.digimbanking.Features.Auth.CreateRekening.Cif

import androidx.lifecycle.ViewModel
import com.core.domain.model.PekerjaanItemModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.domain.model.DataNik
import com.core.domain.model.NikModel

class CifViewModel : ViewModel() {
    private val _selectedPekerjaan = MutableLiveData<PekerjaanItemModel>()
    val selectedPekerjaan: LiveData<PekerjaanItemModel> = _selectedPekerjaan


    fun validateNik(nik: String): NikModel? {
        var dataNik: NikModel? = null
        DataNik.listNik.forEach {
            if (it.nik == nik)
                dataNik = it
            return@forEach
        }
        return dataNik
    }
}