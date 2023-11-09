package com.digimbanking.Features.Profile.UbahPw

import androidx.lifecycle.ViewModel
import com.core.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class UbahPwViewModel (): ViewModel() {
    var isPasswordLamaValid = false
    var isPasswordBaruValid = false
    var isPasswordKonfValid = false
    var isPasswordBeda = false
    var isPasswordSama = false

    fun validatePasswordLama(passwordLama: String): Boolean{
        isPasswordLamaValid = passwordLama.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return  isPasswordLamaValid
    }
    fun validatePasswordBaru(passwordBaru: String): Boolean{
        isPasswordBaruValid = passwordBaru.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return  isPasswordBaruValid
    }
    fun validatePasswordKonfirm(passwordKonfirm: String): Boolean{
        isPasswordKonfValid = passwordKonfirm.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return isPasswordKonfValid
    }

//    fun validatePasswordBeda(passwordLama: String,passwordBaru: String): Boolean {
//        isPasswordBeda = passwordBaru != passwordLama
//        return isPasswordBeda
//    }
//
//    fun validatePasswordSama(passwordBaru: String, passwordKonfirm: String): Boolean{
//        isPasswordSama = passwordKonfirm.equals(passwordBaru)
//        return isPasswordSama
//    }
}