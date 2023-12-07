package com.digimbanking.Features.Transfer.Riwayat.Riwayat2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.response.riwayatTransaksi.Transaction
import com.digimbanking.Data.Adapter.RiwayatTransakiListAdapter
import com.digimbanking.Features.Transfer.Riwayat.Filter.BottomSheetFilterFragment
import com.digimbanking.Features.Transfer.Riwayat.Resi.ResiActivity
import com.digimbanking.R
import com.digimbanking.databinding.FragmentRiwayatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RiwayatFragment : Fragment(), BottomSheetFilterFragment.DateFilterListener {
    private lateinit var binding: FragmentRiwayatBinding
    private val adapterRiwayat: RiwayatTransakiListAdapter by lazy { RiwayatTransakiListAdapter() }
    private lateinit var dataRiwayat: MutableList<Transaction>
    private lateinit var viewModel: RiwayatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRiwayatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        binding.rvTransaksiRiwayat.layoutManager = layoutManager
        binding.rvTransaksiRiwayat.adapter = adapterRiwayat

        viewModel = ViewModelProvider(this)[RiwayatViewModel::class.java]
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            onLoading()
            activity?.let {
                viewModel.doRiwayat(true,true, "", "")
                    .observe(viewLifecycleOwner){
                        when(it){
                            is Result.Success -> {
                                dataRiwayat = it.data.transactions.toMutableList()
                                dataRiwayat.sortBy { it.nama }
                                adapterRiwayat.submitList(it.data.transactions)
                                binding.apply {
                                    imageEmptyListRiwayat.setImageResource(R.drawable.empty_list_riwayat)
                                    tvEmptyTransaksi.text = it.data.transactions.toString()

                                    if (it.data.transactions.isEmpty()) {
                                        imageEmptyListRiwayat.visibility = View.VISIBLE
                                        tvEmptyTransaksi.visibility = View.VISIBLE
                                        tvEmptyTransaksi.text = "Belum ada transaksi"
                                    } else {
                                        imageEmptyListRiwayat.visibility = View.GONE
                                        tvEmptyTransaksi.visibility = View.GONE
                                    }
                                }
                                Log.d("Isi data Riwayat", "${it.data}")
                                onFinishedLoading()
                            }
                            is Result.Error -> {
                                Log.d("Error get Riwayat", it.errorMessage)
                                onFinishedLoading()
                            }
                            else -> {
                                Log.d("Test", "JSON empty")
                                onLoading()
                            }
                        }
                    }
            }
        }

        binding.fabAutoScrollTop.setOnClickListener {
            binding.rvTransaksiRiwayat.smoothScrollToPosition(0)
        }

        adapterRiwayat.setOnClickItem(rvClickListener)
        binding.cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor(("#918AFF")))
        binding.tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor("#FFFFFF"))
        binding.cvFilter.setOnClickListener {

            val bottomSheetFilter = BottomSheetFilterFragment()
            bottomSheetFilter.dateFilterListener = this@RiwayatFragment
            bottomSheetFilter.show(childFragmentManager, "show dialog")
        }

        binding.cvRiwayatTransaksiSemua.setOnClickListener() {
            adapterRiwayat.submitList(dataRiwayat)
            onSelected("#918AFF", "#FFFFFF", "#F3F7FD",
                "#202327", "#F3F7FD", "#202327")
        }

        binding.cvRiwayatTransaksiMasuk.setOnClickListener() {
            val filteredRiwayatTransaksiMasuk = dataRiwayat.filter {
                it.tipeTransaksi.contains("KREDIT")
            }
            adapterRiwayat.submitList(filteredRiwayatTransaksiMasuk)
            onSelected("#F3F7FD", "#202327", "#918AFF",
                "#FFFFFF", "#F3F7FD", "#202327")
        }

        binding.cvRiwayatTransaksiKeluar.setOnClickListener() {
            val filteredRiwayatTransaksiKeluar = dataRiwayat.filter {
                it.tipeTransaksi.contains("DEBIT")
            }
            adapterRiwayat.submitList(filteredRiwayatTransaksiKeluar)
            onSelected("#F3F7FD", "#202327", "#F3F7FD",
                "#202327", "#918AFF", "#FFFFFF")
        }
    }

    private val rvClickListener: (Transaction) -> Unit = { item ->
        startActivity(Intent(activity, ResiActivity::class.java).apply {
            putExtra("riwayat", item)
        })
    }

    private fun onSelected(
        firstText: String, firstBg: String,
        secondtext: String, secondBg: String,
        thirdText: String, thirdBg: String
    )
    {
        binding.apply {
            cvRiwayatTransaksiSemua.setCardBackgroundColor(Color.parseColor(firstText))
            tvSemuaRiwayatTransaksi.setTextColor(Color.parseColor(firstBg))
            cvRiwayatTransaksiMasuk.setCardBackgroundColor(Color.parseColor(secondtext))
            tvMasukRiwayatTransaksi.setTextColor(Color.parseColor(secondBg))
            cvRiwayatTransaksiKeluar.setCardBackgroundColor(Color.parseColor(thirdText))
            tvKeluarRiwayatTransaksi.setTextColor(Color.parseColor(thirdBg))
        }
    }

    override fun filteredByDateHistory(start: String, end: String) {
        Log.d("Isi Instance Riwayat", "$start $end")
        viewModel.doRiwayat(true,true, start, end)
            .observe(viewLifecycleOwner){ result ->
                when(result){
                    is Result.Success -> {
                        dataRiwayat = result.data.transactions.toMutableList()
                        adapterRiwayat.submitList(result.data.transactions)
                        binding.apply {
                            imageEmptyListRiwayat.setImageResource(R.drawable.empty_list_riwayat)
                            tvEmptyTransaksi.text = result.data.transactions.toString()

                            if (result.data.transactions.isEmpty()) {
                                imageEmptyListRiwayat.visibility = View.VISIBLE
                                tvEmptyTransaksi.visibility = View.VISIBLE
                            } else {
                                imageEmptyListRiwayat.visibility = View.GONE
                                tvEmptyTransaksi.visibility = View.GONE
                            }
                        }
                        Log.d("Isi data Riwayat", "${result.data}")
                    }
                    is Result.Error -> {
                        Log.d("Error get Riwayat", result.errorMessage)
                    }
                    else -> {
                        Log.d("Test", "JSON empty")
                    }
                }
            }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onLoading(){
        binding.loadScreen.visibility = View.VISIBLE
        binding.loadScreen.setOnTouchListener { _, _ ->
            true
        }
    }

    private fun onFinishedLoading(){
        binding.loadScreen.visibility = View.GONE
    }
}