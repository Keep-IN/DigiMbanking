package com.core.data.response.auth.createRekening.cif


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CifResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("norek")
    val norek: String,
    @SerializedName("status")
    val status: Int
) : Parcelable