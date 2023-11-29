package com.core.data.response.riwayatTransaksi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val jumlahTransaksi: String,
    val kodeTransaksi: Int,
    val nama: String,
    val tanggal: String,
    val tipeTransaksi: String
): Parcelable