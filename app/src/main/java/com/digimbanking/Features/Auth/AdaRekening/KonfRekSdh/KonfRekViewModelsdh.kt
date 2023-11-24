package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.repositories.RekeningRepositorySdh
import com.core.data.network.Result
import com.core.data.response.authAdaRekening.KonfirmasiRekening.RekeningResponse
import com.core.domain.model.Rekening
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class KonfRekViewModelsdh @Inject constructor(
    private val rekeningRepository: RekeningRepositorySdh
) : ViewModel() {

    private val CheckRekeningResult = MutableLiveData<Result<RekeningResponse>>()
    val checkRekeningResult = CheckRekeningResult

    fun checkRekening(
        noRekening: Long
    ) {
        rekeningRepository.checkRekening(noRekening).observeForever { result ->
            checkRekeningResult.value = result
        }
    }
}
