package com.digimbanking.Features.Transfer.Riwayat.DetailRiwayat

import com.core.domain.model.RiwayatGetResponse

interface DetailResiContract{

    fun onSuccessGetStatus(riwayat: RiwayatGetResponse?)

    fun onErrorGetStatus(msg: String)
}