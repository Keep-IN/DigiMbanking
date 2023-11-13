package com.digimbanking.Features.Transfer.Riwayat.Mutasi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.domain.model.DataRiwayat
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.Data.Adapter.RiwayatTransaksiListAdapter
import com.digimbanking.Data.Model.DataRiwayatUnused
import com.digimbanking.Data.Model.RiwayatModel
import com.digimbanking.Features.Transfer.Riwayat.Filter.BottomSheetFilterFragment
import com.digimbanking.Features.Transfer.Riwayat.Resi.ResiActivity
import com.digimbanking.databinding.FragmentRiwayatBinding

class RiwayatFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatBinding
    private val adapterRiwayat: RiwayatTransaksiListAdapter by lazy { RiwayatTransaksiListAdapter() }
    private lateinit var dataRiwayat: MutableList<RiwayatItemModel>

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
        dataRiwayat = DataRiwayat.riwayattransaksiList
        binding.rvTransaksiRiwayat.adapter = adapterRiwayat
        binding.rvTransaksiRiwayat.layoutManager = layoutManager
        adapterRiwayat.submitList(dataRiwayat)
        adapterRiwayat.setOnClickItem(rvClickListener)
        binding.cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor(("#918AFF")))
        binding.tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
        binding.cvFilter.setOnClickListener {
            fragmentManager?.let { it1 -> BottomSheetFilterFragment().show(it1, "test") }
        }

        binding.cvRiwayatTransaksiSemua.setOnClickListener() {
            adapterRiwayat.submitList(dataRiwayat)
            binding.apply {
                cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor(("#918AFF")))
                tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor("#F3F7FD"))
                tvMasukRiwayatTransaksi.setTextColor(Color.parseColor("#202327"))
                cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor("#F3F7FD"))
                tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor("#202327"))
            }
        }

        binding.cvRiwayatTransaksiMasuk.setOnClickListener() {
            val filteredRiwayatTransaksiMasuk = dataRiwayat.filter {
                it.tipeTransaksi.contains("kredit")
            }
            adapterRiwayat.submitList(filteredRiwayatTransaksiMasuk)
            binding.apply {
                cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor("#F3F7FD"))
                tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#202327"))
                cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor("#918AFF"))
                tvMasukRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
                cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor("#F3F7FD"))
                tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor("#202327"))
            }
        }

        binding.cvRiwayatTransaksiKeluar.setOnClickListener() {
            val filteredRiwayatTransaksiKeluar = dataRiwayat.filter {
                it.tipeTransaksi.contains("debit")
            }
            adapterRiwayat.submitList(filteredRiwayatTransaksiKeluar)
            binding.apply {
                cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor("#F3F7FD"))
                tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#202327"))
                cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor("#F3F7FD"))
                tvMasukRiwayatTransaksi.setTextColor(Color.parseColor("#202327"))
                cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor("#918AFF"))
                tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
    private val rvClickListener: (RiwayatItemModel) -> Unit = { item ->
        startActivity(Intent(activity, ResiActivity::class.java).apply {
            putExtra("riwayat", item)
        })
    }
}