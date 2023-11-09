package com.digimbanking.Features.Transfer.Riwayat.Dashboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.digimbanking.Data.Adapter.RiwayatTransaksiListAdapter
import com.digimbanking.databinding.FragmentBerandaBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BerandaFragment : Fragment() {
    private val adapterRiwayat: RiwayatTransaksiListAdapter by lazy{ RiwayatTransaksiListAdapter()}
    private lateinit var binding: FragmentBerandaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        var isSwitchOn = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        adapterRiwayat.submitList(RiwayatModel.riwayatList)
        binding.rvListRiwayatTerakhir.adapter = adapterRiwayat
        binding.rvListRiwayatTerakhir.layoutManager = layoutManager

        binding.ivIcPaste.setOnClickListener{
            val clipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val value = binding.tvNorek.text.toString()
            clipboardManager.setPrimaryClip(ClipData.newPlainText("data", value))
            Toast.makeText(context, "Nomor rekening telah disalin", Toast.LENGTH_SHORT).show()
        }
        binding.apply{
            ivEyeSharp.setOnClickListener {
                binding.ivEyeClosed.isVisible = true
                binding.ivEyeSharp.isVisible = false
            }
            ivEyeClosed.setOnClickListener {
                binding.ivEyeSharp.isVisible = true
                binding.ivEyeClosed.isVisible = true
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