//package com.digimbanking.Features.Transfer.Riwayat.DatePicker
//
//import android.app.DatePickerDialog
//import android.app.Dialog
//import android.os.Bundle
//import android.view.View
//import android.widget.DatePicker
//import androidx.fragment.app.DialogFragment
//import androidx.fragment.app.setFragmentResult
//import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView
//import com.digimbanking.Features.Transfer.Riwayat.Filter.BottomSheetFilterFragment
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Locale
//
//    class DatePicker :  DialogFragment(), DatePickerDialog.OnDateSetListener {
////    private lateinit var binding: DialogDateBinding
//    private val calendar = Calendar.getInstance()
//    private val calendarFun: DateRangeCalendarView by lazy { DateRangeCalendarView(requireContext())}
//
////    private lateinit var sharedPreferencesHelper: SharedPreferencesH//    private val PREF_NAME = "DateRangePickerPrefs"
//////    private val KEY_START_DATE = "start_date"
//////    private val KEY_END_DATE = "end_date"
//////elper
////
////    private val preferences: SharedPreferences =
////        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//
////    fun saveDateRange(startDate: Date, endDate: Date){
////        val editor = preferences.edit()
////        editor.putLong(KEY_START_DATE, startDate.time)
////        editor.putLong(KEY_END_DATE, endDate.time)
////        editor.apply()
////    }
////
////    fun getDateRange(): Pair<Date, Date>{
////        val startDateMillis = preferences.getLong(KEY_START_DATE, 0)
////        val endDateMillis = preferences.getLong(KEY_END_DATE, 0)
////
////        val startDate = Date(startDateMillis)
////        val endDate = Date(endDateMillis)
////
////        return Pair(startDate, endDate)
////    }
//
////    private var tanggalMulai: String = " "
////    private var tanggalAkhir: String = " "
////    private val tglFormat = SimpleDateFormat("d-M-YYYY", Locale("id,", "ID"))
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        // default date
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        // return new DatePickerDialog instance
//        return DatePickerDialog(requireActivity(), this, year, month, day)
//    }
//
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        calendar.set(Calendar.YEAR, year)
//        calendar.set(Calendar.MONTH, month)
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
//        val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(calendar.time)
//
//        val selectedDateBundle = Bundle()
//        selectedDateBundle.putString("SELECTED_DATE", selectedDate)
//        val bottomSheet = BottomSheetFilterFragment.newInstance(selectedDateBundle.toString())
//        bottomSheet.show(requireActivity().supportFragmentManager, "Date Range Picker")
//
//        setFragmentResult("REQUEST_KEY", selectedDateBundle)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        binding = DialogDateBinding.bind(view)
////        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
////
////        val dateRangePicker = dateRangePicker.setCalendarListener(this)
////
////        val (startDate, endDate) = sharedPreferencesHelper.getDateRange()
////        dateRangePicker.getSelectedDateRange(startDate, endDate)
//
//        val startMonth = Calendar.getInstance()
//        startMonth.set(2023, Calendar.DECEMBER, 20)
//        val endMonth = startMonth.clone() as Calendar
//        endMonth.add(Calendar.MONTH, 5)
//
//        calendarFun.setVisibleMonthRange(startMonth, endMonth)
//
//        val startDateSelectable = startMonth.clone() as Calendar
//        startDateSelectable.add(Calendar.DATE, 20)
//        val endDateSelectable = endMonth.clone() as Calendar
//        endDateSelectable.add(Calendar.DATE, -20)
//
//        calendarFun.setSelectableDateRange(startDateSelectable, endDateSelectable)
//
//        val startSelectedDate = startDateSelectable.clone() as Calendar
//        startSelectedDate.add(Calendar.DATE, 10)
//        val endSelectedDate = endDateSelectable.clone() as Calendar
//        endSelectedDate.add(Calendar.DATE, -10)
//
//        calendarFun.setSelectedDateRange(startSelectedDate, endSelectedDate)
//
//        val current = startMonth.clone() as Calendar
//        current.add(Calendar.MONTH, 1)
//        calendarFun.setCurrentMonth(current)
//
//
//        binding.apply {
//                // create new instance of DatePickerFragment
//                val datePickerFragment = DatePicker()
//                val supportFragmentManager = requireActivity().supportFragmentManager
//
//                // we have to implement setFragmentResultListener
//                supportFragmentManager.setFragmentResultListener(
//                    "REQUEST_KEY",
//                    viewLifecycleOwner
//                ) { resultKey, bundle ->
//                    if (resultKey == "REQUEST_KEY") {
//                        val date = bundle.getString("SELECTED_DATE")
//                        if (date != null) {
//                            BottomSheetFilterFragment.newInstance(date)
//                        }
//                    }
//                }
//
//                // show
//                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
//            }
//
////        override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar){
////            sharedPreferencesHelper.saveDateRange(startDate.time, endDate.time)
////        }
//
//        }
//    }