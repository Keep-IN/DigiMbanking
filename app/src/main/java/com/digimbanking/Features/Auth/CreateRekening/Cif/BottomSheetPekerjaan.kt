package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.core.domain.model.ListPekerjaan
import com.core.domain.model.ListPekerjaan.pekerjaanList
import com.core.domain.model.PekerjaanItemModel
import com.digimbanking.Data.Adapter.PekerjaanAdapter
import com.digimbanking.R
import com.digimbanking.databinding.BottomSheetPekerjaanBinding


//class BottomSheetPekerjaan : SuperBottomSheetFragment() {
//    lateinit var binding :BottomSheetPekerjaanBinding
//    private lateinit var sharedPreferences: SharedPreferences
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//        binding = BottomSheetPekerjaanBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.btnBack.setOnClickListener {
//            val intent = Intent(requireContext(), BuatAkun::class.java)
//            startActivity(intent)
//        }
//
//        showjob(requireContext(), pekerjaanList)
//        sharedPreferences = requireContext().getSharedPreferences("pekerjaan", Context.MODE_PRIVATE)
//    }
//
//    private fun showjob(context: Context, dataPekerjaan: List<PekerjaanItemModel>) {
//        val pekerjaanAdapter = PekerjaanAdapter(context, dataPekerjaan)
//        binding.rvListPekerjaan.adapter = pekerjaanAdapter
//        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        binding.rvListPekerjaan.layoutManager = layoutManager
//    }
//
//    override fun isSheetAlwaysExpanded() = true
//
//    override fun getExpandedHeight() = -2
//}

class BottomSheetPekerjaan : SuperBottomSheetFragment() {
    var pekerjaanListener: PekerjaanListener? = null
    lateinit var binding: BottomSheetPekerjaanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetPekerjaanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            val intent = Intent(requireContext(), BuatAkun::class.java)
            startActivity(intent)
        }

        showjob(requireContext(), pekerjaanList)
    }

    private fun showjob(context: Context, dataPekerjaan: List<PekerjaanItemModel>) {
        val pekerjaanAdapter = PekerjaanAdapter(context, dataPekerjaan)
        binding.rvListPekerjaan.adapter = pekerjaanAdapter
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvListPekerjaan.layoutManager = layoutManager
    }

    interface PekerjaanListener {
        fun onPekerjaanSelected(selectedPekerjaan: PekerjaanItemModel)
    }

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}


