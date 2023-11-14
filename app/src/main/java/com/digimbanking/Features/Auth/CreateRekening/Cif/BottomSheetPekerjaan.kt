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
import com.core.domain.model.ListPenghasilan
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Data.Adapter.PekerjaanAdapter
import com.digimbanking.Data.Adapter.PenghasilanAdapter
import com.digimbanking.R
import com.digimbanking.databinding.BottomSheetPekerjaanBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetPekerjaan : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetPekerjaanBinding
    private lateinit var pekerjaanAdapter: PekerjaanAdapter
    private val pekerjaanList =ListPekerjaan.pekerjaanList

    interface PekerjaanListener {
        fun onPekerjaanSelected(selectedPekerjaan: PekerjaanItemModel)
    }
    var pekerjaanListener: PekerjaanListener? = null

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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        pekerjaanAdapter = PekerjaanAdapter { selectedPekerjaan ->
            pekerjaanListener?.onPekerjaanSelected(selectedPekerjaan)
            dismiss()
        }

        binding.rvListPekerjaan.layoutManager = LinearLayoutManager(context)
        binding.rvListPekerjaan.adapter = pekerjaanAdapter
        pekerjaanAdapter.setPenghasilanList(pekerjaanList)
    }
}


