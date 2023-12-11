package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.core.domain.model.ListPenghasilan
import com.core.domain.model.ListPenghasilan.penghasilanList
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Data.Adapter.PenghasilanAdapter
import com.digimbanking.databinding.BottomSheetPenghasilanBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetPenghasilan : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetPenghasilanBinding
    private lateinit var penghasilanAdapter: PenghasilanAdapter
    private val penghasilanList = ListPenghasilan.penghasilanList

    interface PenghasilanListener {
        fun onPenghasilanSelected(selectedPenghasilan: PenghasilanItemModel)
    }
    var penghasilanListener: PenghasilanListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetPenghasilanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.btnBack.setOnClickListener {
            dismiss()
        }
    }

    private fun setupRecyclerView() {
        penghasilanAdapter = PenghasilanAdapter { selectedPenghasilan ->
            penghasilanListener?.onPenghasilanSelected(selectedPenghasilan)
            dismiss()
        }

        binding.rvPenghasilan.layoutManager = LinearLayoutManager(context)
        binding.rvPenghasilan.adapter = penghasilanAdapter
        penghasilanAdapter.setPenghasilanList(penghasilanList)
    }
}