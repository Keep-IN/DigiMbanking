package com.digimbanking.Features.Transfer.SesamaBank

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
                onLoading()
                when(result){
                    is Result.Success -> {
                        dataNasabah = result.data.data
                        binding.apply {
                            tvRekSumber.text = result.data.data.rekening.joinToString { it.noRekening }
                            tvSaldoSumber.text = "Rp ${result.data.data.rekening.joinToString { it.saldo.toLong().formatDotSeparator() }}"
                            when(result.data.data.rekening.joinToString { it.tipeRekening.idTipe.toString() }){
                                "1" -> {
                                    cvBgCardSumber.setCardBackgroundColor(Color.parseColor("#C0C0C0"))
                                    ivCardTypeLogo.setImageResource(R.drawable.gpn)
                                }
                                "2" -> {
                                    cvBgCardSumber.setCardBackgroundColor(Color.parseColor("#FBDB2F"))
                                    ivCardTypeLogo.setImageResource(R.drawable.ic_visa_logo)
                                }
                                "3" -> {
                                    cvBgCardSumber.setCardBackgroundColor(Color.parseColor("#696865"))
                                    ivCardTypeLogo.setImageResource(R.drawable.platinum)
                                }
                            }
                        }
                        onFinishedLoading()
                    }
                    is Result.Error -> {
                        Toast.makeText(this@InputNominal, result.errorMessage, Toast.LENGTH_SHORT).show()
                        onFinishedLoading()
                    } else -> {
                        Log.d("Unexpected Error", "$result")
                        onLoading()
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
            val saldo = dataNasabah.rekening.joinToString { it.saldo.toLong().toString() }
            val limit = dataNasabah.rekening.joinToString { it.tipeRekening.limitTransfer }
            if (txNominal.toBigInteger() <= saldo.toBigInteger()){
                if (txNominal.toBigInteger() <= limit.toBigInteger()) {
                    startActivity(Intent(this, MpinSesama::class.java).apply {
                        putExtra("data", TransactionModel(txCatatan, "", "", dataRekening, txNominal.toInt()))
                        putExtra("akun", dataNasabah)
                    })
                } else {
                    binding.tvWarningNominal.apply {
                        setTextColor(Color.parseColor("#E71414"))
                        text = "Limit transfer Rp ${limit.toLong().formatDotSeparator()}"
                    }
                }
            } else {
                binding.tvWarningNominal.apply {
                    setTextColor(Color.parseColor("#E71414"))
                    text = "Saldo tidak mencukupi"
                }
            }
        }

        binding.tilNominal.editText?.apply {
            setNoLeadingZeroFilter(this)
            doOnTextChanged { text, start, before, count ->
                if (text != null) {
                    if (text.isNotEmpty()){
                        if(viewModel.validateNoninal(text.toString())) {
                            validateInput()
                            binding.apply {
                                tvWarningNominal.setTextColor(Color.parseColor("#323A43"))
                                tvWarningNominal.text = "Minimal Transfer Rp10.000"
                            }
                        } else {
                            binding.apply {
                                tvWarningNominal.setTextColor(Color.parseColor("#E71414"))
                            }
                        }
                    }
                }
            }
        }
    }
    private fun capitalizeWords(input: String?): String{
        val words = input?.split(" ")
        val capitalizedWords = words?.map { it.capitalize() }
        var output: String = ""
        if (capitalizedWords != null) {
            output =  capitalizedWords.joinToString(" ")
        }
        return output
    }

    private fun setNoLeadingZeroFilter(editText: EditText) {
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

    private fun Long.formatDotSeparator(): String{
        return toString()
            .reversed()
            .chunked(3)
            .joinToString (".")
            .reversed()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onLoading(){
        binding.loadScreen.visibility = View.VISIBLE
        binding.loadScreen.setOnTouchListener { _, _ ->
            true
        }
    }

    private fun onFinishedLoading(){
        binding.loadScreen.visibility = View.GONE
    }
}