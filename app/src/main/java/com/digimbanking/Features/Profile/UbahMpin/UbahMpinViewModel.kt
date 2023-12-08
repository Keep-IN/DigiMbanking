package com.digimbanking.Features.Profile.UbahMpin

class UbahMpinViewModel {
    var isMpinLamaValid = false
    var isMpinBaruValid = false
    var isMpinKonfValid = false
    var isMpinBeda = false
    var isMpinSama = false

    fun validateMpinLama(mpinLama: String): Boolean{
        isMpinLamaValid = mpinLama.contains ("^[0-9]{6}$".toRegex())
        return  isMpinLamaValid
    }
    fun validateMpinBaru(mpinBaru: String): Boolean{
        isMpinBaruValid = mpinBaru.contains ("^[0-9]{6}$".toRegex())
        return  isMpinBaruValid
    }
    fun validateMpinKonfirm(mpinKonfirm: String): Boolean{
        isMpinKonfValid = mpinKonfirm.contains ("^[0-9]{6}$".toRegex())
        return isMpinKonfValid
    }

    fun validateMpinBeda(mpinLama: String, mpinBaru: String): Boolean {
        isMpinBeda = mpinBaru != mpinLama
        return isMpinBeda
    }

    fun validateMpinSama(mpinBaru: String, mpinKonfirm: String): Boolean{
        isMpinSama = mpinKonfirm.equals(mpinBaru)
        return isMpinSama
    }
}