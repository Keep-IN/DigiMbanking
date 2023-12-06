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
import com.core.data.response.akun.Rekening
import com.core.data.response.riwayatTransaksi.Transaction
import com.digimbanking.Data.Adapter.RiwayatTransaksiListBerandaAdapter
import com.digimbanking.Features.Akun.AkunViewModel
import com.digimbanking.Features.Transfer.Riwayat.Riwayat2.RiwayatViewModel
import com.digimbanking.Features.Transfer.SesamaBank.RekTujuan
import com.digimbanking.R
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
    private lateinit var viewModelAkun: AkunViewModel
    private var isBalanceVisible = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivEyeSharp.isVisible = true
            ivEyeClosed.isVisible = false
            tvSaldoSum.text = "Tekan untuk melihat"
        }

        viewModelAkun = ViewModelProvider(this)[AkunViewModel::class.java]
        viewModelAkun.viewModelScope.launch(Dispatchers.Main){
            activity?.let {
                viewModelAkun.doAkun()
                    .observe(viewLifecycleOwner){ result ->
                        when(result){
                            is Result.Success -> {
                                binding.apply {
                                    val saldo = "Rp ${result.data.data.rekening.joinToString { it.saldo.toLong().formatDotSeparator() }}"
                                    tvInisialAkun.text = result.data.data.name.first().toString()
                                    tvProfil.text = result.data.data.name
                                    tvIsianNorek.text = result.data.data.rekening.joinToString { it.noRekening.formatDashSeparator() }
                                    ivEyeClosed.setOnClickListener {
                                        toggleAccountBalance(saldo)
                                    }
                                    ivEyeSharp.setOnClickListener {
                                        toggleAccountBalance(saldo)
                                    }
                                }
                            }
                            is Result.Error -> {
                                Log.d("Error get User", result.errorMessage)
                            }
                            else -> {
                                Log.d("Test", "JSON empty")
                            }
                        }
                    }
            }
        }

        viewModel = ViewModelProvider(this)[RiwayatViewModel::class.java]
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            activity?.let {

                viewModel.doRiwayat(true,true, "", "")
                    .observe(viewLifecycleOwner){ result ->
                        when(result){
                            is Result.Success -> {
                                dataRiwayat = result.data.transactions.toMutableList()
                                adapterRiwayat.submitList(result.data.transactions)
                                binding.apply {
                                    ivEmptyListRiwayat.setImageResource(R.drawable.empty_list_riwayat)
                                    tvBelumAdaTransaksi.text = result.data.transactions.toString()

                                    if (dataRiwayat.isEmpty()) {
                                        ivEmptyListRiwayat.visibility = View.VISIBLE
                                        tvBelumAdaTransaksi.visibility = View.VISIBLE
                                    } else {
                                        ivEmptyListRiwayat.visibility = View.GONE
                                        tvBelumAdaTransaksi.visibility = View.GONE
                                    }
                                }
                                Log.d("Isi data home", "${result.data}")
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
            ivTransfer.setOnClickListener {
                startActivity(Intent(activity, RekTujuan::class.java))
            }
        }
     }
    private fun toggleAccountBalance(nominal: String){
        binding.apply {
            if(isBalanceVisible){
                ivEyeSharp.isVisible = true
                ivEyeClosed.isVisible = false
                tvSaldoSum.text = "Tekan untuk melihat"
            }
            else{
                ivEyeClosed.isVisible = true
                ivEyeSharp.isVisible = false
                tvSaldoSum.text = nominal
            }
            isBalanceVisible = !isBalanceVisible
        }
    }
    fun Long.formatDotSeparator(): String{
        return toString()
            .reversed()
            .chunked(3)
            .joinToString (".")
            .reversed()
    }
    fun String.formatDashSeparator(): String{
        return toString()
            .chunked(4)
            .joinToString("-")
    }
}