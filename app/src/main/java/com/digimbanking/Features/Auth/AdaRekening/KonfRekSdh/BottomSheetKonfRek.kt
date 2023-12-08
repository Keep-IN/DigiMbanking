package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.core.data.network.Result
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.BuatSandiSudah
import com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh.KonfirmasiEmailSudah
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.AlertDialog.AlertUnregsdh
import com.digimbanking.R
import com.digimbanking.databinding.BottomSheetKonfRekBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
    class BottomSheetKonfRek(private val namaLengkap: String, private val nik: String,private val rekening: String) : SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetKonfRekBinding
    private lateinit var viewModel: KonfRekViewModelsdh

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetKonfRekBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvnamaRek.text = namaLengkap
        binding.tvnoRek.text = nik

        viewModel = ViewModelProvider(this).get(KonfRekViewModelsdh::class.java)
        binding.button3.setOnClickListener{}

        binding.button3.setOnClickListener {

            viewModel.viewModelScope.launch(Dispatchers.Main) {
                viewModel.confirmUsercif(rekening).observe(this@BottomSheetKonfRek, Observer { result ->
                    when (result) {
                        is Result.Success -> {
                            handleSuccess()
                        }

                        is Result.Error -> {
                            AlertUnregsdh().show(parentFragmentManager, "test")
                        }

                        is Result.Loading -> {

                        }
                    }
                })
            }


        }


    }
        private fun handleSuccess() {
            startActivity(Intent(activity, BuatSandiSudah::class.java))
            requireActivity().finishAffinity()
        }

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}