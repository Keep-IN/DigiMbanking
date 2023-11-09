package com.digimbanking.Features.Transfer.Riwayat.bottomsheetdaterangepicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.digimbanking.Features.Transfer.Riwayat.Dashboard.BerandaFragment
import com.digimbanking.R
import com.digimbanking.databinding.FragmentBottomSheetDateRangePickerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale

class BottomSheetDateRangePickerFragment :  BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetDateRangePickerBinding

    companion object {
        val bottomTag: String = "TAG_DATE_PICKER"
    }
    private val setDateViewModel: BottomSheetDateRangePickerViewModel by viewModels()
    private val tanggalMulai: String = ""
    private val tanggalAkhir: String = ""
    private val tglFormat = SimpleDateFormat("d-M-YYYY", Locale("id,","ID"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBottomSheetDateRangePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(BerandaFragment.isSwitchOn){
            binding.calendar.visibility = View.VISIBLE
            binding.calendarSingle.visibility = View.VISIBLE
        }else{
            binding.calendar.visibility = View.GONE
            binding.calendarSingle.visibility = View.VISIBLE
        }
    }
}