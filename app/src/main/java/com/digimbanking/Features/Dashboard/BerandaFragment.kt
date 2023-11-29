package com.digimbanking.Features.Dashboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.response.riwayatTransaksi.Transaction
import com.core.domain.model.DataRiwayat
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.Data.Adapter.RiwayatTransaksiListBerandaAdapter
import com.digimbanking.Features.Transfer.Riwayat.Mutasi.RiwayatViewModel
import com.digimbanking.Features.Transfer.SesamaBank.RekTujuan
import com.digimbanking.databinding.FragmentBerandaBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BerandaFragment : Fragment() {
    private val adapterRiwayat: RiwayatTransaksiListBerandaAdapter by lazy{ RiwayatTransaksiListBerandaAdapter()}
    private lateinit var binding: FragmentBerandaBinding
    private lateinit var layoutEmptyTransaction: RelativeLayout
    private lateinit var dataRiwayat: MutableList<Transaction>
    private lateinit var viewModel: RiwayatViewModel
    private var isBalanceVisible = true

    private fun toggleAccountBalance(){
        binding.apply {
            if(isBalanceVisible){
                ivEyeSharp.isVisible = true
                ivEyeClosed.isVisible = false
                tvSaldoSum.text = "Tekan untuk melihat saldo"
            }else{
                ivEyeClosed.isVisible = true
                ivEyeSharp.isVisible = false
                tvSaldoSum.text = "Rp500.000.000" // masukkan data response nya
            }
            isBalanceVisible = !isBalanceVisible
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RiwayatViewModel::class.java]
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            activity?.let {

                viewModel.doRiwayat(true,true, "", "")
                    .observe(viewLifecycleOwner){
                        when(it){
                            is Result.Success -> {
                                dataRiwayat = it.data.transactions.toMutableList()
                                adapterRiwayat.submitList(it.data.transactions)
                                Log.d("Isi data home", "${it.data}")
                            }
                            is Result.Error -> {
                                Log.d("Error get Riwayat", it.errorMessage)
                            }
                            else -> {
                                Log.d("Test", "JSON empty")
                            }
                        }
                    }
            }
        }

        val layoutManager = LinearLayoutManager(activity)

        binding.rvListRiwayatTerakhir.adapter = adapterRiwayat
        binding.rvListRiwayatTerakhir.layoutManager = layoutManager

        binding.ivIcPaste.setOnClickListener{
            val clipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val value = binding.tvIsianNorek.text.toString()
            clipboardManager.setPrimaryClip(ClipData.newPlainText("data", value))
            Toast.makeText(context, "Nomor rekening telah disalin", Toast.LENGTH_SHORT).show()
        }

        binding.apply{
            ivEyeClosed.setOnClickListener {
                toggleAccountBalance()
            }
            ivEyeSharp.setOnClickListener {
                toggleAccountBalance()
            }
            ivTransfer.setOnClickListener {
                startActivity(Intent(activity, RekTujuan::class.java))
            }
        }

        fun Long.formatDotSeparator(): String{
            return toString()
                .reversed()
                .chunked(3)
                .joinToString (".")
                .reversed()
        }

        fun Long.formatDashSeparator(): String{
            return toString()
                .chunked(4)
                .joinToString { "-" }
        }

     }
}