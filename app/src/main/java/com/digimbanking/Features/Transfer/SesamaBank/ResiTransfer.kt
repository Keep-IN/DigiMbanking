package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.core.data.response.transferSesama.TransactionResponse
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.R
import com.digimbanking.databinding.ActivityResiTransferBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ResiTransfer : AppCompatActivity() {
    private lateinit var binding: ActivityResiTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataResi = intent.getParcelableExtra<TransactionResponse>("dataResi")
        binding.apply {
            if (dataResi != null) {
                val convertedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val originalDate: Date = convertedDate.parse(dataResi.dataTransaksi.timeTransaksi)
                val targetDateFormat = SimpleDateFormat("dd MMM yyyy | HH:mm", Locale.US)
                tvSenderName.text = dataResi.pengirim.nama
                tvInitialSender.text = dataResi.pengirim.nama.first().toString()
                tvSenderRekening.text = "${dataResi.pengirim.namaBank} - ${dataResi.pengirim.noRekening.toString()}"
                tvRecieverName.text = dataResi.penerima.nama
                tvInitialReciever.text = dataResi.penerima.nama.first().toString()
                tvrecieverRekening.text = "${dataResi.penerima.namaBank} - ${dataResi.penerima.noRekening.toString()}"
                tvRefNumb.text = dataResi.dataTransaksi.id.toString()
                tvTimeStamp.text = targetDateFormat.format(originalDate).toString()
                tvAdminFee.text = "Rp ${dataResi.dataTransaksi.biayaAdmin.toLong().toString()}"
                tvSum.text = "Rp ${dataResi.dataTransaksi.totalTransaksi}"
                tvTransType.text = dataResi.dataTransaksi.jenisTransaksi
                if (dataResi.dataTransaksi.catatan.isEmpty()){
                    tvCatatan.text = "Tidak ada"
                } else {
                    tvCatatan.text = dataResi.dataTransaksi.catatan
                }
            }
        }
        binding.btnSelesaiResi.setOnClickListener {
            startActivity(Intent(this, NavbarContainer::class.java))
            finishAffinity()
        }
    }
}