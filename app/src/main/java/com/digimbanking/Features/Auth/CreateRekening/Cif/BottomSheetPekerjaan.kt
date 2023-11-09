package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.core.domain.model.ListPekerjaan
import com.core.domain.model.PekerjaanItemModel
import com.digimbanking.Data.Adapter.PekerjaanAdapter
import com.digimbanking.databinding.BottomSheetPekerjaanBinding


class BottomSheetPekerjaan : SuperBottomSheetFragment() {
    private val sharedViewModel: CifViewModel by activityViewModels()
    lateinit var binding :BottomSheetPekerjaanBinding
    private val pekerjaanAdapter : PekerjaanAdapter by lazy { PekerjaanAdapter() }
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
        dialog?.setCanceledOnTouchOutside(true)
        binding.btnBack.setOnClickListener {
            dialog?.setCancelable(true)
        }
        binding.rvListPekerjaan.adapter = pekerjaanAdapter
        binding.rvListPekerjaan.layoutManager = LinearLayoutManager(requireContext())
        pekerjaanAdapter.submitList(ListPekerjaan.pekerjaanList)
        pekerjaanAdapter.setOnPekerjaanClickListener(rvClickListener)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private val rvClickListener: (PekerjaanItemModel) -> Unit = { item ->
        val intent = Intent()
        intent.putExtra("pekerjaan", item)
        requireActivity().setResult(Activity.RESULT_OK, intent)
        requireActivity().finish()
    }

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
    fun setTargetFragment(buatAkun: BuatAkun, i: Int) {
        TODO("Not yet implemented")
    }
}

private fun PekerjaanAdapter.setOnPekerjaanClickListener(rvClickListener: (PekerjaanItemModel) -> Unit) {

}
