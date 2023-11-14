package com.digimbanking.Features.Auth.AdaRekening.MpinSdh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinViewModel:ViewModel() {
    private val _pin = MutableLiveData<String>()
    val pin: LiveData<String> get() = _pin
    fun setPin(pin: String) {
        _pin.value = pin
    }
}