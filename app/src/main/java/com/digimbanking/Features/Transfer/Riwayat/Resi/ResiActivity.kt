package com.digimbanking.Features.Transfer.Riwayat.Resi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.riwayatTransaksi.Transaction
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.databinding.ActivityResiBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResiBinding
    private lateinit var viewModel: ResiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ResiViewModel::class.java]
        setContentView(binding.root)
        val dataTransaksi = intent.getParcelableExtra<Transaction>("riwayat")
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            if (dataTransaksi != null) {
                viewModel.doResi(dataTransaksi.kodeTransaksi).observe(this@ResiActivity){result ->
                    when(result){
                        is Result.Success -> {
                            binding.apply {
                                tvNamaPengirim.text = result.data.pengirim.nama
                                tvBankPengirim.text = "${result.data.pengirim.namaBank} - ${result.data.pengirim.noRekening.toString()}"
                                tvInisialPengirim.text = result.data.pengirim.nama.first().toString()
                                tvIsianNomorReferensi.text = result.data.data.kodeTransaksi.toString()
                                tvIsianTipeTransaksi.text = result.data.data.tipeTransaksi
                                tvIsianBiayaAdmin.text = result.data.data.biayaAdmin.toString()
                                tvIsianTotalTransaksi.text = result.data.data.totalTransaksi.toString()
                                tvIsianCatatan.text = result.data.data.catatan
                                tvNamaPenerimaResi.text = result.data.penerima.nama
                                tvBankPenerimaResi.text = "${result.data.penerima.namaBank} - ${result.data.penerima.noRekening.toString()}"
                                tvInisialPenerimaResi.text = result.data.penerima.nama.first().toString()
                            }
                        }

                        is Result.Error -> {
                            Log.d("Error get Resi", result.errorMessage)
                        }

                        else -> {
                            Log.d("Test", "JSON empty")
                        }
                    }
                }
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