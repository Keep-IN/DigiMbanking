package com.digimbanking.Features.Transfer.Riwayat.Filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
//import com.digimbanking.Features.Transfer.Riwayat.DatePicker.DatePicker
import com.digimbanking.R
import com.digimbanking.databinding.FragmentBottomSheetFilterBinding
import com.google.android.material.datepicker.MaterialDatePicker
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Transfer.Riwayat.Riwayat2.RiwayatViewModel
import com.google.android.material.datepicker.CalendarConstraints
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class BottomSheetFilterFragment : SuperBottomSheetFragment() {
    private lateinit var binding: FragmentBottomSheetFilterBinding
    private lateinit var viewModel: RiwayatViewModel
    private var dataDateStart: String? = null
    private var dataDateEnd: String? = null
    companion object {
        fun newInstance(dateStart: String, dateEnd: String): BottomSheetFilterFragment{
            val fragment = BottomSheetFilterFragment()
            fragment.arguments = Bundle().apply {
                putString("dateStart", dateStart)
                putString("dateEnd", dateEnd)
            }
            return fragment
        }
    }
    interface DateFilterListener{
        fun filteredByDateHistory(start: String, end: String)
    }
    var dateFilterListener: DateFilterListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentBottomSheetFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RiwayatViewModel::class.java]
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val constraints = CalendarConstraints.Builder()
            .setStart(today)
            .setEnd(today)
            .build()
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .build()

        binding.btnClose.setOnClickListener {
            dialog?.cancel()
        }

        binding.tilDateStart.editText?.setOnClickListener {
            childFragmentManager.let { fragmentManager ->
                dateRangePicker.show(fragmentManager, dateRangePicker.toString())
            }
            dateRangePicker.addOnPositiveButtonClickListener {
                dataDateStart = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    .format(Date(it.first))
                dataDateEnd = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    .format(Date(it.second))
                binding.tilDateStart.editText?.setText(dataDateStart)
                binding.tilDateEnd.editText?.setText(dataDateEnd)
            }
        }

        binding.tilDateEnd.editText?.setOnClickListener{
            childFragmentManager.let { fragmentManager ->
                dateRangePicker.show(fragmentManager, dateRangePicker.toString())
            }
            dateRangePicker.addOnPositiveButtonClickListener {
                val formattedStartDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    .format(Date(it.first))
                val formattedEndDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    .format(Date(it.second))
                binding.tilDateStart.editText?.setText(formattedStartDate)
                binding.tilDateEnd.editText?.setText(formattedEndDate)
            }

        }


        binding.rgFilter.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId){
                R.id.rb_7hari_terakhir -> handleFilter7HariSelection()
                R.id.rb_15hari_terakhir -> handleFilter15HariSelection()
                R.id.rb_tanggal -> handleFilterTanggalSelection()
            }
        }

        binding.apply {
            tilDateStart.isEnabled = false
            tilDateEnd.isEnabled = false

        }

        binding.btnTerapkan.setOnClickListener{
            dateFilterListener?.filteredByDateHistory(dataDateStart.toString(), dataDateEnd.toString())
            dismiss()
        }
    }
    private fun handleFilter7HariSelection(){
        Toast.makeText(requireContext(), "Filter 7 hari", Toast.LENGTH_SHORT).show()
        if(binding.rb7hariTerakhir.isSelected){
            binding.rb7hariTerakhir.isSelected = false
        }else{
            binding.apply {
                rb7hariTerakhir.isSelected = true
                rb15hariTerakhir.isSelected = false
                rbTanggal.isSelected = false
                tilDateStart.isEnabled = false
                tilDateEnd.isEnabled = false

                val calendar = Calendar.getInstance()
                dataDateEnd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
                calendar.add(Calendar.DAY_OF_MONTH, -6) // 7 days ago
                dataDateStart = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)

                tilDateStart.editText?.setText(dataDateStart)
                tilDateEnd.editText?.setText(dataDateEnd)
            }
        }
    }

    private fun handleFilter15HariSelection(){
        Toast.makeText(requireContext(), "Filter 15 hari", Toast.LENGTH_SHORT).show()
        if(binding.rb15hariTerakhir.isSelected){
            binding.rb15hariTerakhir.isSelected = false
        }else{
            binding.apply {
                rb7hariTerakhir.isSelected = false
                rb15hariTerakhir.isSelected = true
                rbTanggal.isSelected = false
                tilDateStart.isEnabled = false
                tilDateEnd.isEnabled = false

                val calendar = Calendar.getInstance()
                dataDateEnd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
                calendar.add(Calendar.DAY_OF_MONTH, -14) // 15 days ago
                dataDateStart = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)

                tilDateStart.editText?.setText(dataDateStart)
                tilDateEnd.editText?.setText(dataDateEnd)
            }
        }
    }

    private fun handleFilterTanggalSelection(){
        Toast.makeText(requireContext(), "Filter pilih tanggal", Toast.LENGTH_SHORT).show()
        if(binding.rbTanggal.isSelected){
            binding.rbTanggal.isSelected = false
        } else {
            binding.apply {
                rb7hariTerakhir.isSelected = false
                rb15hariTerakhir.isSelected = false
                rbTanggal.isSelected = true
                tilDateStart.isEnabled = true
                tilDateEnd.isEnabled = true
            }
        }
    }

    override fun isSheetAlwaysExpanded() = true
    override fun getExpandedHeight() = -2
}