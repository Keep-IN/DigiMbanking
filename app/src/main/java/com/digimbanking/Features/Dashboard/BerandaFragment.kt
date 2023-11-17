package com.digimbanking.Features.Dashboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.domain.model.DataRiwayat
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.Data.Adapter.RiwayatTransaksiListBerandaAdapter
import com.digimbanking.Features.Transfer.SesamaBank.RekTujuan
import com.digimbanking.databinding.FragmentBerandaBinding

class BerandaFragment : Fragment() {
    private val adapterRiwayat: RiwayatTransaksiListBerandaAdapter by lazy{ RiwayatTransaksiListBerandaAdapter()}
    private lateinit var binding: FragmentBerandaBinding
    private lateinit var dataRiwayat : MutableList<RiwayatItemModel>
    private lateinit var layoutEmptyTransaction: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    companion object {
//        var isSwitchOn = false
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        dataRiwayat = DataRiwayat.riwayattransaksiList
        adapterRiwayat.submitList(DataRiwayat.riwayattransaksiList)
        binding.rvListRiwayatTerakhir.adapter = adapterRiwayat
        binding.rvListRiwayatTerakhir.layoutManager = layoutManager



        binding.ivIcPaste.setOnClickListener{
            val clipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val value = binding.tvIsianNorek.text.toString()
            clipboardManager.setPrimaryClip(ClipData.newPlainText("data", value))
            Toast.makeText(context, "Nomor rekening telah disalin", Toast.LENGTH_SHORT).show()
        }

//private fun copyToClipboard() {
//    val editText = binding.tvNorek.editText
//
//    if (editText != null) {
//        val textToCopy = editText.text.toString()
//
//        if (textToCopy.isNotBlank()) {
//            val clipboardManager =
//                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText("Teks yang disalin", textToCopy)
//            clipboardManager.setPrimaryClip(clipData)
//
//            Toast.makeText(
//                this@NomorRekening,
//                "Nomor rekening berhasil disalin ke clipboard",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            Toast.makeText(
//                this@NomorRekening,
//                "Nomor rekening tidak tersedia",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//}
//        binding.tvNorek.setOnClickListener {
//            copyToClipboard()
//        }

        binding.apply{
            ivEyeClosed.setOnClickListener {
                binding.ivEyeSharp.isVisible = true
                binding.ivEyeClosed.isVisible = false
            }
            ivEyeSharp.setOnClickListener {
                binding.ivEyeClosed.isVisible = true
                binding.ivEyeSharp.isVisible = false
            }
            ivTransfer.setOnClickListener {
                startActivity(Intent(activity, RekTujuan::class.java))
            }
        }
//        if (dataRiwayat.isEmpty()){
//            binding.ivEmptyListRiwayat.isVisible = true
//            binding.tvBelumAdaTransaksi.isVisible = true
//        }else{
//            binding.ivEmptyListRiwayat.isVisible = true
//            binding.tvBelumAdaTransaksi.isVisible = false
//        }


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

//        val emptyHistory = getEmptyHistory()
//
//        if (emptyHistory.isNotEmpty()){
//          //  recyclerView.visibility = View.VISIBLE
//            layoutEmptyTransaction.visibility = View.GONE
//
//            val adapter = RiwayatTransaksiListAdapter()
//            recyclerView.adapter = adapterRiwayat
//            recyclerView.layoutManager = LinearLayoutManager(context)
//        }else{
//            recyclerView.visibility = View.GONE
//            layoutEmptyTransaction.visibility = View.VISIBLE
//        }
//    }

//    private fun getEmptyHistory(): List<RiwayatTransaksiModel>{
//        return listOf(
//
//        )
     }
}