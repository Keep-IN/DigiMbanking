package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.AlertDialog.AlertUnregsdh
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiEmailSudahBinding
import com.digimbanking.databinding.ActivityKonfirmasiRekSudahBinding

class KonfirmasiRekSudah : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiRekSudahBinding
    private lateinit var viewModel: KonfRekViewModelsdh
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfirmasiRekSudahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(KonfRekViewModelsdh::class.java)

        binding.btnKonfrek.setOnClickListener {
            val nomorRekening = binding.etNorek.text.toString()
            viewModel.cekNomorRekening(nomorRekening)
        }

        viewModel.rekeningLiveData.observe(this, Observer { rekening ->
            if (rekening != null) {
                val bottomSheetFragment = BottomSheetKonfRek(rekening.namaPemilik, rekening.nomorRekening, rekening.inisialPemilik)
                bottomSheetFragment.show(supportFragmentManager, "KonfirmasiRekeningBottomSheet")
            } else {
                AlertUnregsdh().show(supportFragmentManager,"test")
            }
        })
    }
}