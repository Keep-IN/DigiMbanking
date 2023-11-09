package com.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatItemModel(
    val id: Int,
    val tipeTransaksi: String,
    val nama: String,
    val jumlahTransaksi: Int,
    val timeTransaction: String,
): Parcelable