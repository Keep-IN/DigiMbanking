package com.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class
ProfilModel (
    val nama: String,
    val norek: String,
    val nik: String,
    val email: String
) : Parcelable