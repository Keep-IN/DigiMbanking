//package com.core.data.repositories
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.liveData
//import com.core.data.local.entity.User
//import com.core.data.local.preferences.UserPreferences
//import com.core.di.ApiContractLogin
//import kotlinx.coroutines.flow.first
//import javax.inject.Inject
//import javax.inject.Singleton
//
//// Define API Function Here
//
//@Singleton
//class UserRepository @Inject constructor(
//    private val apiService: ApiContractLogin,
//    private val userPreferences: UserPreferences,
//){
//    fun getDetailUser(): LiveData<Result<User>> {
//        return liveData {
//            emit(Result.Loading)
//            val currentUser = userPreferences.getUser().first()
//            try {
//                val response = apiService.getDetailUser(currentUser.id)
//                val newUser = currentUser.copy(
//                    name = response.name,
//                    email = response.email,
//                    photoUrl = response.photoUrl,
//                )
//                emit(Result.Success(newUser))
//            } catch (e: Exception) {
//                Log.d("getDetailUser", e.message.toString())
//                emit(Result.Error(e.toString()))
//            }
//        }
//    }