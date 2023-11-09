package com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KonfirmasiMPINViewModelsdh : ViewModel() {
    private val _konfirmasiPin = MutableLiveData<String>()
    val konfirmasiPin: LiveData<String> get() = _konfirmasiPin

    fun setKonfirmasiPin(pin: String) {
        _konfirmasiPin.value = pin
    }
}