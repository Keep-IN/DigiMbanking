package com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh

import androidx.lifecycle.ViewModel

class KonfirmasiEmailsdhViewModel : ViewModel() {
    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(email)
    }
}