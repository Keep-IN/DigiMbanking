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
    fun putMPIN (
        mpin : String
    ) = createMPIN.putMPIN(mpin)
}