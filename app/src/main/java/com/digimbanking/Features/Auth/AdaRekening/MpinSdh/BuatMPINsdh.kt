package com.digimbanking.Features.Auth.AdaRekening.MpinSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.KonfirmasiMPINsdh
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaos.view.PinView
import com.core.data.network.Result
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.BuatSandiSudah
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfirmasiRekSudah
import com.digimbanking.Features.Auth.CreateRekening.Mpin.KonfirmasiMpin
import com.digimbanking.Features.Auth.OnBoard.OnBoard2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuatMPINsdh : AppCompatActivity() {
    private lateinit var binding: ActivityBuatMpinsdhBinding
    private lateinit var viewModel:PinViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuatMpinsdhBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PinViewModel::class.java)
        sharedPreferences = getSharedPreferences("Mpin", MODE_PRIVATE)
        binding.btnBackMPIN.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        val pinView = binding.sendMPIN
        pinView.addTextChangedListener {
            it?.let {
                viewModel.setPin(it.toString())
                if (it.length == pinView.itemCount) {
                    val intent = Intent(this, KonfirmasiMPINsdh::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.pin.value?.let {
            sharedPreferences.edit().putString("pin", it).apply()
        }
    }

}
