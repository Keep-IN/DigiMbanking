package com.core.data.response.transferSesama


import com.google.gson.annotations.SerializedName

data class DataTransaksi(
    @SerializedName("biaya_admin")
    val biayaAdmin: Int,
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("kode_transaksi")
    val kodeTransaksi: Int,
    @SerializedName("tipe_transaksi")
    val tipeTransaksi: String,
    @SerializedName("total_transaksi")
    val totalTransaksi: Double
)