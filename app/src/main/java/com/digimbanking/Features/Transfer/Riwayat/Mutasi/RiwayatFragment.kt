package com.digimbanking.Features.Transfer.Riwayat.Mutasi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.domain.model.RiwayatGetResponse
import com.digimbanking.Data.Adapter.RiwayatTransaksiListAdapter
import com.digimbanking.Data.Model.RiwayatItemModel
import com.digimbanking.Features.Transfer.Riwayat.Resi.ResiActivity
import com.digimbanking.databinding.FragmentRiwayatBinding

class RiwayatFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatBinding
    private val adapterRiwayat: RiwayatTransaksiListAdapter by lazy { RiwayatTransaksiListAdapter()}
    private lateinit var dataRiwayat : List<RiwayatItemModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
       binding = FragmentRiwayatBinding.inflate(layoutInflater, container, false)
        val progressBar = binding.progressBar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        binding.rvTransaksiRiwayat.adapter = adapterRiwayat
        binding.rvTransaksiRiwayat.layoutManager = layoutManager
        adapterRiwayat.setOnClickItem(rvClickListener)

        binding.cvRiwayatTransaksiSemua.setOnClickListener(){
            adapterRiwayat.submitList(dataRiwayat)
            binding.apply {
                cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor(("#918AFF")))
                tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor("#6C63FF"))
                tvMasukRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFF"))
                cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor("#6C63FF"))
                tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFF"))
            }
        }

        binding.cvRiwayatTransaksiMasuk.setOnClickListener(){
            val filteredRiwayatTransaksiMasuk = dataRiwayat.filter {
                it.tipeTransaksi.contains("Transaksi Masuk") ?: false
            }
            adapterRiwayat.submitList(filteredRiwayatTransaksiMasuk)
            binding.apply {
                cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor("#6C63FF"))
                tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor("#918AFF"))
                tvMasukRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor("#6C63FF"))
                tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        binding.cvRiwayatTransaksiKeluar.setOnClickListener(){
            val filteredRiwayatTransaksiKeluar = dataRiwayat.filter {
                it.tipeTransaksi.contains("Transaksi Keluar") ?: false
            }
            adapterRiwayat.submitList(filteredRiwayatTransaksiKeluar)
            binding.apply {
                cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor("#6C63FF"))
                tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor("#6C63FF"))
                tvMasukRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor("#918AFF"))
                tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        private val rvClickListener: (RiwayatItemModel) -> Unit = {
            item ->
            startActivity(Intent(activity, ResiActivity::class.java).apply {
                putExtra("riwayat", item)
            })
        }

        override fun onSuccess(riwayat: RiwayatGetResponse?){
            if(riwayat != null){
                adapterRiwayat.submitList(riwayat.riwayat)
                dataRiwayat = riwayat.riwayat
            }
        }

        override fun onFailed(msg: String){
            Log.d("Error", "Gagal menampilkan riwayat")
        }
    }
}