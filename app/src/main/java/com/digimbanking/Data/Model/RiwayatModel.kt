package com.digimbanking.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatModel(
    val id: Int,
    val tipeTransaksi: String,
    val nama: String,
    val jumlahTransaksi: Int,
    val timeTransaction: String,
): Parcelable
