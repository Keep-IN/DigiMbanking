package com.digimbanking.Features.Auth.CreateRekening.Mpin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MpinViewModel : ViewModel() {
    private val _pin = MutableLiveData<String>()
    private val _konfirmasiPin = MutableLiveData<String>()
    val konfirmasiPin: LiveData<String> get() = _konfirmasiPin
    val pin: LiveData<String> get() = _pin
    fun setPin(pin: String) {
        _pin.value = pin
    }

    fun setKonfirmasiPin(pin: String) {
        _konfirmasiPin.value = pin
    }
}