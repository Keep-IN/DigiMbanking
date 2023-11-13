package com.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class RiwayatItemModel(
    val id: Int,
    val tipeTransaksi: String,
    val namaPenerima: String,
    val bankPenerima: String,
    val norekPenerima: String,
    val namaPengirim: String,
    val bankPengirim: String,
    val norekPengirim: Int,
    val jumlahTransaksi: Int,
    val timeTransaction: String,
    val nomorReferensi: Int,
    val biayaAdmin: Int
): Parcelable