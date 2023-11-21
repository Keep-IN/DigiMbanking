package com.digimbanking.Features.Transfer.Riwayat.Resi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.databinding.ActivityResiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataTransaksi = intent.getParcelableExtra<RiwayatItemModel>("riwayat")
        if (dataTransaksi != null) {
            binding.apply {
                tvBankPenerimaResi.text = "${dataTransaksi.bankPenerima} - ${dataTransaksi.norekPenerima}"
                tvBankPengirim.text = "${dataTransaksi.bankPengirim} - ${dataTransaksi.norekPengirim}"
                tvNamaPenerimaResi.text = dataTransaksi.namaPenerima
                tvNamaPengirim.text = dataTransaksi.namaPengirim
                tvIsianNomorReferensi.text = dataTransaksi.nomorReferensi.toString()
                tvIsianTipeTransaksi.text = dataTransaksi.tipeTransaksi
                tvIsianBiayaAdmin.text = "Rp ${dataTransaksi.biayaAdmin.formatDotSeparator()}"
                tvIsianTotalTransaksi.text = "Rp ${dataTransaksi.jumlahTransaksi.formatDotSeparator()}"
            }
        }
    }
    fun Int.formatDotSeparator(): String{
        return toString()
            .reversed()
            .chunked(3)
            .joinToString (".")
            .reversed()
    }
}