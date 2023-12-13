package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.core.data.repositories.RekeningRepositorySdh
import com.core.data.network.Result
import com.core.data.repositories.PasswordRepositroySdh
import com.core.data.response.authAdaRekening.BuatKataSandisdh.KataSandiResponse
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningResponse
import com.core.domain.model.Rekening
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class KonfRekViewModelsdh @Inject constructor(
    private val rekeningRepository: RekeningRepositorySdh,
    private val passworRepository: PasswordRepositroySdh
) : ViewModel() {

    var isPasswordValid = false

    private val CheckRekeningResult = MutableLiveData<Result<RekeningResponse>>()
    val checkRekeningResult = CheckRekeningResult


    fun checkRekening(
        noRekening: String
    ) {
        rekeningRepository.checkRekening(noRekening).observeForever { result ->
            checkRekeningResult.value = result
        }
    }

    fun putPass(
        password: String
    ) = passworRepository.putNewpass(password)

    fun confirmUsercif(
        noRekening: String
    ) = rekeningRepository.createUserCif(noRekening)

    fun validatePassLength(password: String): Boolean{
        return  password.length >=8
    }

    fun validatePassword(password: String): Boolean{
        isPasswordValid = password.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return  isPasswordValid
    }

    fun isRekeningValid(accountNumber: String): Boolean {
        val accountNumberRegex = Regex("\\d{10,16}")
        return accountNumberRegex.matches(accountNumber)
    }

}
