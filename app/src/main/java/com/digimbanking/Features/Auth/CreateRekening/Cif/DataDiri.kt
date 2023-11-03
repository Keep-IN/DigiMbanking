package com.digimbanking.Features.Transfer.TransferSesama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.R
import com.digimbanking.databinding.ActivityRekTujuanBinding

class RekTujuan : AppCompatActivity() {
    private lateinit var binding : ActivityRekTujuanBinding
    private lateinit var viewModel: RekTujuanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRekTujuanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[RekTujuanViewModel::class.java]
        binding.tilNorekTujuan.editText?.isEnabled = false
        binding.tilPilihBank.editText?.doAfterTextChanged {
            validateRekField()
        }

        binding.tilNorekTujuan.editText?.doOnTextChanged { text, start, before, count ->
            validateInput()
        }

        binding.btnLanjut.setOnClickListener {
            viewModel.validateRekening(binding.tilNorekTujuan.editText?.text.toString())
        }
    }

    private fun validateInput(){
        binding.btnLanjut.isEnabled = binding.tilNorekTujuan.editText?.text.toString().isNotBlank() &&
                binding.tilPilihBank.editText?.text.toString().isNotBlank()
    }

    private fun validateRekField(){
        binding.tilNorekTujuan.isEnabled = binding.tilPilihBank.editText?.text.toString().isNotBlank()
    }
}