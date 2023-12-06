package com.core.data.response.akun


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataRekeningAkun(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("accounts")
    val rekening: List<Rekening>
): Parcelable