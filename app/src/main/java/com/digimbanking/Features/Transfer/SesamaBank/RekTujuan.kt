package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.listbank.DataBank
import com.core.domain.model.BankItemModel
import com.digimbanking.Features.Transfer.SesamaBank.BottomSheet.BottomSheetDetailPenerima
import com.digimbanking.databinding.ActivityRekTujuanBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RekTujuan : AppCompatActivity() {
    private lateinit var binding : ActivityRekTujuanBinding
    private lateinit var viewModel: RekTujuanViewModel
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
            validateInput()
        }

        binding.btnLanjut.setOnClickListener {
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                viewModel.postRekening(txRekening).observe(this@RekTujuan){
                    when(it){
                        is Result.Success -> {
                            val data = it.data.data
                            val bottomSheet = BottomSheetDetailPenerima.newInstance(data.nama, data.noRekening.toString(), data.namaBank)
                            bottomSheet.show(supportFragmentManager, "Detail Rekening Tujuan")
                        }
                        is Result.Error -> {
                            Toast.makeText(this@RekTujuan, it.errorMessage, Toast.LENGTH_SHORT).show()
                            Log.d("Error Post", it.errorMessage)
                        }
                        else -> {
                            Log.d("Unexpected Result", "Received an unexpected result: $it")
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
}