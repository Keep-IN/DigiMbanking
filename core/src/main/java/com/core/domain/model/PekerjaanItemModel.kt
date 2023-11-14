package com.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PekerjaanItemModel(
    val id : Int,
    val nama : String
) : Parcelable
