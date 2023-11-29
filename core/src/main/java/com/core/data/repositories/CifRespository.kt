package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.lokal.preferences.UserPreferences
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.cif.CifModel
import com.core.data.response.auth.createRekening.cif.CifResponse
import com.core.di.ApiContractCreateRekening
import com.core.domain.model.DataCard
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CifRespository @Inject constructor(
    private val apiService : ApiContractCreateRekening,
    private val userPreferences: UserPreferences
) {

    fun postCif(
        nik: String,
        namaLengkap : String,
        alamat : String,
        pekerjaan : String,
        penghasilan : String
    ) : LiveData<Result<CifResponse>> = liveData {
        emit(Result.Loading)
        try {
            val id = userPreferences.getUser()
            val response = apiService.postCif(id, DataCard.id,CifModel(nik, namaLengkap, alamat, pekerjaan, penghasilan))
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                emit(Result.Error("Failed to get a valid response"))
            }
        } catch (e : Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}