package com.digimbanking.Features.Auth.CreateRekening.Mpin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.repositories.MpinRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MpinViewModel @Inject constructor(
    private val mpinRepository: MpinRepository
) : ViewModel(){

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

    fun putMpin(
        mpin: String
    ) = mpinRepository.putMpin(mpin)
}