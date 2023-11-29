package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.lokal.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.pilihKartu.CardResponse
import com.core.data.response.auth.createRekening.pilihKartu.DataCard
import com.core.di.ApiContractCreateRekening
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository  @Inject constructor(
    private val apiService : ApiContractCreateRekening,
    private val userPreferences: UserPreferences
){
    fun getCard () :LiveData<Result<CardResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getTipeKartu()
            val tipe = response.data.map {
                DataCard(
                    idTipe = it.idTipe,
                    limitTransfer = it.limitTransfer,
                    namaTipe = it.namaTipe

                )

            }
            val cardResponse = CardResponse(data = tipe)
            if (response != null) {
                userPreferences.setCardId(response.data[0].idTipe)
            }
            emit(Result.Success(cardResponse))
        } catch (e: Exception ) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}