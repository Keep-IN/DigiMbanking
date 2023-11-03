package com.digimbanking.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatItemModel(
    val id: Int,
    val tipe_transaksi: String,
    val nama: String,
    val jumlah_transaksi: Int,
    val time_transaction: String,
): Parcelable
