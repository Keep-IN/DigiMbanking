package com.digimbanking.Features.Profile.UbahPw

import androidx.lifecycle.ViewModel
import com.core.data.repositories.UbahPwRepository
import com.core.domain.model.DataLogin
import com.core.domain.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UbahPwViewModel @Inject constructor(
    private val ubahPwRepository: UbahPwRepository
): ViewModel() {
    var isPasswordLamaValid = false
    var isPasswordBaruValid = false
    var isPasswordKonfValid = false
    var isPasswordBeda = false
    var isPasswordSama = false
    var isPanjangPwLama = false
    var isPanjangPwBaru = false
    var isPanjangPwKonf = false

    fun ubahPw(
        pwLama: String,
        pwBaru: String,
        konfirmPw: String
    ) = ubahPwRepository.ubahPw(pwLama, pwBaru, konfirmPw)

    fun validatePasswordLama(passwordLama: String): Boolean{
        isPasswordLamaValid = passwordLama.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]\$".toRegex())
        return  isPasswordLamaValid
    }
    fun validatePasswordBaru(passwordBaru: String): Boolean{
        isPasswordBaruValid = passwordBaru.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]\$".toRegex())
        return  isPasswordBaruValid
    }
    fun validatePasswordKonfirm(passwordKonfirm: String): Boolean{
        isPasswordKonfValid = passwordKonfirm.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]\$".toRegex())
        return isPasswordKonfValid
    }

    fun validatePasswordLamaLength(passwordLama: String): Boolean{
        isPanjangPwLama = passwordLama.length > 8
        return isPanjangPwLama
    }

    fun validatePasswordBaruLength(passwordBaru: String): Boolean{
        isPanjangPwBaru = passwordBaru.length > 8
        return isPanjangPwBaru
    }

    fun validatePasswordKonfLength(konfirmPw: String): Boolean{
        isPanjangPwKonf = konfirmPw.length > 8
        return isPanjangPwKonf
    }

//    fun validatePasswordLogin(password: String): LoginModel?{
//        var dataLogin: LoginModel? = null
//        DataLogin.listUserLogin.forEach {
//            if (it.password == password)
//                dataLogin = it
//            return@forEach
//        }
//        return dataLogin
//    }

    fun validatePasswordBeda(passwordLama: String, passwordBaru: String): Boolean {
        isPasswordBeda = passwordBaru != passwordLama
        return isPasswordBeda
    }

    fun validatePasswordSama(passwordBaru: String, passwordKonfirm: String): Boolean{
        isPasswordSama = passwordKonfirm.equals(passwordBaru)
        return isPasswordSama
    }
}