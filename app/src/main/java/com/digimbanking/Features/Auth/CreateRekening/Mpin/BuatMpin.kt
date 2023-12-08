package com.digimbanking.Features.Auth.CreateRekening.Mpin

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.R
import com.chaos.view.PinView
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.KataSandi
import com.digimbanking.databinding.ActivityBuatMpinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuatMpin : AppCompatActivity() {
    private lateinit var binding : ActivityBuatMpinBinding
    private lateinit var mpinViewModel: MpinViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatMpinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mpinViewModel = ViewModelProvider(this).get(MpinViewModel::class.java)
        sharedPreferences = getSharedPreferences("Mpin", MODE_PRIVATE)

        val pinView = binding.sendMpin
        pinView.addTextChangedListener {
            it?.let {
                mpinViewModel.setPin(it.toString())
                if (it.length == pinView.itemCount) {
                    val intent = Intent(this, KonfirmasiMpin::class.java)
                    startActivity(intent)
                }
            }
        }

        binding.btnKembali.setOnClickListener {
            startActivity(Intent(this, KataSandi::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        mpinViewModel.pin.value?.let {
            sharedPreferences.edit().putString("pin", it).apply()
        }
    }
}