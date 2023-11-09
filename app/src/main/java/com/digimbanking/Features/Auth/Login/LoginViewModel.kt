package com.digimbanking.Features.Auth.Login

import androidx.lifecycle.ViewModel
import com.core.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    var isEmailValid = false
    var isPasswordValid = false
    fun validateEmail(email: String): Boolean{
        isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
        return isEmailValid
    }

    fun validatePassword(password: String): Boolean{
        isPasswordValid = password.contains ("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return  isPasswordValid
    }
}