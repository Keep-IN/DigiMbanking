package com.digimbanking.Features.Auth.Login

import androidx.lifecycle.ViewModel
import com.core.data.repositories.LoginRepository
import com.core.domain.model.DataLogin
import com.core.domain.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    fun login(
        email: String,
        password: String
    ) = loginRepository.login(email,password)

    var isEmailValid = false
    var isPasswordValid = false

    fun validateEmail(email: String): Boolean{
        isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
        return isEmailValid
    }

    fun validatePassword(password: String): Boolean{
        isPasswordValid = password.contains("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        //("^[0-9]{8}$".toRegex())
        //("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())

        return  isPasswordValid
    }

//    fun validateLogin(email: String, password: String): LoginModel? {
//        var dataLogin: LoginModel? = null
//        DataLogin.listUserLogin.forEach {
//            if (it.email == email && it.password == password)
//                dataLogin = it
//            return@forEach
//        }
//        return dataLogin
//    }
}