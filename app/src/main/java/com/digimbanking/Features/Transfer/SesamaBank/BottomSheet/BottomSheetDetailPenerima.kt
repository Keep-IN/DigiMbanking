package com.digimbanking.Features.Transfer.SesamaBank.BottomSheet

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.digimbanking.Features.Transfer.SesamaBank.InputNominal
import com.digimbanking.Features.Transfer.SesamaBank.ResiTransfer
import com.digimbanking.databinding.BottomSheetRekTujuanBinding

class BottomSheetDetailPenerima: SuperBottomSheetFragment() {
    private lateinit var binding: BottomSheetRekTujuanBinding
    private var dataRekNama: String? = null
    private var dataRekening: String? = null
    private var dataBank: String? = null
    companion object{
        fun newInstance(nama: String, rekening: String, bank: String): BottomSheetDetailPenerima {
            val fragment = BottomSheetDetailPenerima()
            val args = Bundle()
            args.apply{
                putString("nama", nama)
                putString("rekening", rekening)
                putString("bank", bank)
                fragment.arguments = args
                return fragment
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetRekTujuanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRekNama = arguments?.getString("nama")
        dataRekening = arguments?.getString("rekening")
        dataBank = arguments?.getString("bank")
        Log.d("content:", "${dataRekNama}, ${dataRekening}, ${dataBank}")
        binding.apply {
            tvInitialName.text = dataRekNama?.first().toString()
            tvNamaPenerima.text = dataRekNama
            tvRekeningPenerima.text = "${dataBank} - ${dataRekening}"
        }
        binding.btnLanjutRek.setOnClickListener {
            startActivity(Intent(activity, InputNominal::class.java).apply {
                putExtra("nama", dataRekNama)
                putExtra("bank", dataBank)
                putExtra("rekening", dataRekening)
            })
            Handler(Looper.getMainLooper()).postDelayed({
                dialog?.cancel()
            }, 800)
        }
        binding.tvUbahPenerima.setOnClickListener {
            dialog?.cancel()
        }
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}