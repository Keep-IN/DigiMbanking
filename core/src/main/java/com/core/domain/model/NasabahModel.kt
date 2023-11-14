package com.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasabahModel(
    val rekening: String,
    val bank: String,
    val nama: String
): Parcelable
