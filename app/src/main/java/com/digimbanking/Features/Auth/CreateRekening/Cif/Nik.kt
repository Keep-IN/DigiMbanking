package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.domain.model.NikModel
import com.digimbanking.Features.Auth.CreateRekening.Card.NomorRekening
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.Otp
import com.digimbanking.R
import com.digimbanking.databinding.ActivityNik2Binding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Nik : AppCompatActivity() {
    private lateinit var binding: ActivityNik2Binding
    lateinit var cifViewModel: CifViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNik2Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        var data: NikModel? = null

        cifViewModel = ViewModelProvider(this).get(CifViewModel::class.java)
        binding.btnRegist.setOnClickListener {
            cifViewModel.viewModelScope.launch(Dispatchers.Main) {
                cifViewModel.doDukcapil(binding.etNIK.editText?.text.toString())
                    .observe(this@Nik){
                        when(it) {
                            is Result.Success -> {
                                it.data
                                Log.d("Tes", "${it.data}")
                                startActivity(Intent(this@Nik, BuatAkun::class.java).apply {
                                    putExtra("nik", it.data)
                                })
                            }
                            is Result.Error -> {
                                Toast.makeText(
                                    this@Nik,
                                    "${it.errorMessage}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Log.d("Tes", "Empty JSON")
                            }
                        }
                    }
            }
        }

        binding.apply {
            etNIK.editText?.doOnTextChanged { text, start, before, count ->
                if (cifViewModel.validateNik(text.toString())) {
                    binding.etNIK.isErrorEnabled = false
                } else {
                    binding.etNIK.isErrorEnabled = true
                    binding.etNIK.error = "NIK harus terdiri dari 16 digit"
                }
                validateInput()
            }
        }
    }
    private fun validateInput(){
        binding.btnRegist.isEnabled = cifViewModel.validateNik(binding.etNIK.editText?.text.toString())

    }
}