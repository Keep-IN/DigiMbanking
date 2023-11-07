package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.core.domain.model.BankItemModel
import com.digimbanking.Features.Transfer.SesamaBank.BottomSheet.BottomSheetDetailPenerima
import com.digimbanking.databinding.ActivityRekTujuanBinding

class RekTujuan : AppCompatActivity() {
    private lateinit var binding : ActivityRekTujuanBinding
    private lateinit var viewModel: RekTujuanViewModel
    private val txRekening: String get() = binding.tilNorekTujuan.editText?.text.toString()
    private val txBank: String get() = binding.tilPilihBank.editText?.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRekTujuanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dataBank = intent.getParcelableExtra<BankItemModel>("bank")
        if (dataBank != null){
            binding.tilPilihBank.editText?.setText(dataBank.nama)
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
            viewModel.validateRekening(txRekening)
            val data = viewModel.validateCredential(txBank, txRekening)
            if (data != null) {
                val bottomSheet = BottomSheetDetailPenerima.newInstance(data.nama, data.rekening, data.bank)
                bottomSheet.show(supportFragmentManager, "Detail Rekening Tujuan")
            } else {
                Toast.makeText(this, "Nasabah Tidak Ditemukan", Toast.LENGTH_SHORT).show()
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