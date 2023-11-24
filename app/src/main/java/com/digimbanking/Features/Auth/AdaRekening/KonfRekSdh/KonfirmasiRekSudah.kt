package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.core.data.network.Result
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.AlertDialog.AlertUnregsdh
import com.digimbanking.databinding.ActivityKonfirmasiRekSudahBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KonfirmasiRekSudah : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiRekSudahBinding
    private lateinit var viewModel: KonfRekViewModelsdh
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfirmasiRekSudahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(KonfRekViewModelsdh::class.java)

        binding.btnKonfrek.setOnClickListener {
            val noRekening = binding.etNorek.text.toString().toLongOrNull() ?: 0
            viewModel.checkRekening(noRekening)


            viewModel.viewModelScope.launch(Dispatchers.Main) {
                viewModel.checkRekeningResult.observe(this@KonfirmasiRekSudah, Observer { result ->
                    when (result) {
                        is Result.Success -> {
                            val rekeningResponse = result.data
                            val bottomSheetFragment = BottomSheetKonfRek(
                                rekeningResponse.data.namaLengkap,
                                rekeningResponse.data.nik,
                            )
                            bottomSheetFragment.show(
                                supportFragmentManager,
                                "KonfirmasiRekeningBottomSheet"
                            )
                        }

                        is Result.Error -> {
                            AlertUnregsdh().show(supportFragmentManager, "test")
                        }

                        is Result.Loading -> {

                        }
                    }
                })
            }
        }

    }
}