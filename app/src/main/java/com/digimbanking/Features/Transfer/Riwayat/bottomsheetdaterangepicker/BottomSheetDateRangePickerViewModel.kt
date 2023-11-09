package com.digimbanking.Features.Transfer.Riwayat.bottomsheetdaterangepicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BottomSheetDateRangePickerViewModel : ViewModel(){

    fun setTanggalMulai(date: String) = viewModelScope.launch(Dispatchers.IO){preferences.setTanggalMulai(date)}

    fun setTanggalAkhir(date: String) = viewModelScope.launch(Dispatchers.IO){preferences.setTanggalAkhir(date)}
}