package com.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@JsonClass(generateAdapter = true)
data class RiwayatTransaksiModel(
    val id: Int,
    val tipe_transaksi: String,
    val nama: String,
    val jumlah_transaksi: Int,
    val time_transaction: Date,
) : Parcelable