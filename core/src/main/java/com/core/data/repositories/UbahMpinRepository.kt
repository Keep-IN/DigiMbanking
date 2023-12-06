//package com.core.data.repositories
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.liveData
//import com.core.data.network.Result
//import com.core.data.response.Profile.UbahMpin.UbahMpinRequest
//import com.core.data.response.Profile.UbahMpin.UbahMpinResponse
//import com.core.di.ApiContractLogin
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class UbahMpinRepository @Inject constructor(
//    private val apiService: ApiContractLogin
//){
//    fun ubahMpin(
//        mpinLama: String,
//        mpinBaru: String,
//        mpinKonfirm: String
//    ): LiveData<Result<UbahMpinResponse>> = liveData {
//        emit(Result.Loading)
//        val response = apiService.ubahMpin(UbahMpinRequest(mpinLama, mpinBaru, mpinKonfirm))
//
//    }
//}