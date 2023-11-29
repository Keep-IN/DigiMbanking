package com.core.data.response.riwayatResi


import com.google.gson.annotations.SerializedName

data class DataResi(
    @SerializedName("biaya_admin")
    val biayaAdmin: Int,
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("kode_transaksi")
    val kodeTransaksi: Int,
    @SerializedName("tipe_transaksi")
    val tipeTransaksi: String,
    @SerializedName("total_transaksi")
    val totalTransaksi: String
)