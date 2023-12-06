package com.digimbanking.Features.Transfer.SesamaBank

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.akun.AkunResponse
import com.core.data.response.listbank.DataBank
import com.core.domain.model.BankItemModel
import com.digimbanking.Features.Transfer.SesamaBank.BottomSheet.BottomSheetDetailPenerima
import com.digimbanking.Features.Transfer.TransferSesama.AlertDialog.AlertDialogGagal
import com.digimbanking.databinding.ActivityRekTujuanBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RekTujuan : AppCompatActivity() {
    private lateinit var binding : ActivityRekTujuanBinding
    private lateinit var viewModel: RekTujuanViewModel
    private lateinit var dataUser: AkunResponse
    private val txRekening: String get() = binding.tilNorekTujuan.editText?.text.toString()
    private val txBank: String get() = binding.tilPilihBank.editText?.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRekTujuanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dataBank = intent.getParcelableExtra<DataBank>("bank")
        Log.d("Content:", "$dataBank")
        if (dataBank != null){
            binding.tilPilihBank.editText?.setText(dataBank.namaBank)
            validateRekField()
        }
        viewModel = ViewModelProvider(this)[RekTujuanViewModel::class.java]
        binding.tilPilihBank.editText?.setOnClickListener {
            startActivity(Intent(this, ListBank::class.java))
        }

        binding.tilNorekTujuan.editText?.doOnTextChanged { text, start, before, count ->
            if (viewModel.validateRekening(text.toString())){
                validateInput()
                binding.tilNorekTujuan.isErrorEnabled = false
            } else {
                binding.apply {
                    tilNorekTujuan.isErrorEnabled = true
                    tilNorekTujuan.error = "Nomor Rekening Minimal 10 Digit"
                    btnLanjut.isEnabled = viewModel.validateRekening(text.toString())
                }
            }

        }

        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.getUser().observe(this@RekTujuan){
                onLoading()
                when(it){
                    is Result.Success -> {
                        dataUser = it.data
                        onFinishedLoading()
                    }
                    is Result.Error -> {
                        Log.d("Error Get Rekening", "${it.errorMessage}")
                        onFinishedLoading()
                    }
                    else -> {
                        Log.d("Unexpected Result", "Received an unexpected result: $it")
                        onLoading()
                    }
                }
            }
        }
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLanjut.setOnClickListener {
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                viewModel.postRekening(dataUser.data.rekening.joinToString { it.noRekening }, txRekening).observe(this@RekTujuan){
                    onLoading()
                    when(it){
                        is Result.Success -> {
                            val data = it.data.data
                            val bottomSheet = BottomSheetDetailPenerima.newInstance(data.nama, data.noRekening.toString(), data.namaBank)
                            bottomSheet.show(supportFragmentManager, "Detail Rekening Tujuan")
                            onFinishedLoading()
                        }
                        is Result.Error -> {
                            Log.d("Error Post", it.errorMessage)
                            val alert = AlertDialogGagal.newInstance(it.errorMessage)
                            alert.show(supportFragmentManager, "Show alert")
                            onFinishedLoading()
                        }
                        else -> {
                            Log.d("Unexpected Result", "Received an unexpected result: $it")
                            onLoading()
                    }
                    }
                }
            }
        }
    }

    private fun validateInput(){
        binding.btnLanjut.isEnabled = binding.tilNorekTujuan.editText?.text.toString().isNotBlank() &&
                binding.tilPilihBank.editText?.text.toString().isNotBlank()
    }

    private fun validateRekField(){
        binding.tilNorekTujuan.editText?.isEnabled = binding.tilPilihBank.editText?.text.toString().isNotBlank()
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