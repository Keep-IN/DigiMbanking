package com.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NikModel(
    val nik: String,
    val nama: String,
    val alamat: String,
    val nomorRekening: String
) : Parcelable
