package com.core.data.response.authAdaRekening.KonfirmasiRekening

import com.google.gson.annotations.SerializedName

data class DataRekeningResponse
    (@field:SerializedName("nik")
     val nik: String,

     @field:SerializedName("namaLengkap")
     val namaLengkap: String
            )
