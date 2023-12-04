package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.Nasabah.DataUser
import com.core.data.response.akun.DataRekeningAkun
import com.core.data.response.transferSesama.DataNasabahTujuan
import com.core.data.response.transferSesama.TransactionModel
import com.digimbanking.R
import com.digimbanking.databinding.ActivityInputNominalBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InputNominal : AppCompatActivity() {
    private lateinit var binding: ActivityInputNominalBinding
    private lateinit var dataNama: String
    private lateinit var dataRekening: String
    private lateinit var dataBank: String
    private lateinit var viewModel: InputNominalViewModel
    private lateinit var dataNasabah: DataRekeningAkun
    private val txNominal: String get() = binding.tilNominal.editText?.text.toString()
    private val txCatatan: String get() = binding.tilCatatan.editText?.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNominalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[InputNominalViewModel::class.java]
        dataNama = intent.getStringExtra("nama").toString()
        dataBank = intent.getStringExtra("bank").toString()
        dataRekening = intent.getStringExtra("rekening").toString()

        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.getUser().observe(this@InputNominal){ result ->
                when(result){
                    is Result.Success -> {
                        dataNasabah = result.data.data
                    }
                    is Result.Error -> {
                        result.errorMessage
                    } else -> {
                        Log.d("Unexpected Error", "$result")
                    }
                }
            }
        }

        binding.apply {
            tvInitialUserName.text = dataNama.first().toString()
            tvNamaNasabah.text = dataNama
            tvRekeningNasabah.text = "${capitalizeWords(dataBank)} - ${dataRekening}"
            btnToMpin.isEnabled = false
        }

        binding.btnBack1.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnToMpin.setOnClickListener {
            startActivity(Intent(this, MpinSesama::class.java).apply {
                putExtra("data", TransactionModel(txCatatan, "", "", dataRekening, txNominal.toInt()))
            })
        }

        binding.tilNominal.editText?.apply {
            let { setNoLeadingZeroFilter(it) }
            doOnTextChanged { text, start, before, count ->
                validateInput()
            }
        }
    }
    fun capitalizeWords(input: String?): String{
        val words = input?.split(" ")
        val capitalizedWords = words?.map { it.capitalize() }
        var output: String = ""
        if (capitalizedWords != null) {
            output =  capitalizedWords.joinToString(" ")
        }
        return output
    }

    fun setNoLeadingZeroFilter(editText: EditText) {
        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            if (dstart == 0 && source.toString() == "0") {
                ""
            } else {
                null
            }
        }
        editText.filters = arrayOf(inputFilter)
    }

    private fun validateInput(){
        binding.btnToMpin.isEnabled = binding.tilNominal.editText?.text.toString().isNotBlank()
    }
}