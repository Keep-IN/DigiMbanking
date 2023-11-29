package com.digimbanking.Features.Auth.AdaRekening.MpinSdh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.repositories.MPINRepositorySdh
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val createMPIN : MPINRepositorySdh
) :ViewModel(){

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


    fun putMPIN (
        mpin : String
    ) = createMPIN.putMPIN(mpin)
}