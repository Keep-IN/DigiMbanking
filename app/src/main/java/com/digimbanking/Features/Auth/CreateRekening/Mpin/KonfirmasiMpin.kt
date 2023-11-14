package com.digimbanking.Features.Auth.CreateRekening.Mpin

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chaos.view.PinView
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiMpinBinding

class KonfirmasiMpin : AppCompatActivity() {
    private lateinit var binding: ActivityKonfirmasiMpinBinding
    private lateinit var mpinViewModel: MpinViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfirmasiMpinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mpinViewModel = ViewModelProvider(this).get(MpinViewModel::class.java)
        sharedPreferences = getSharedPreferences("Mpin", MODE_PRIVATE)

        val pinView = binding.konfMpin
        val pinErrorText = binding.txtError

        mpinViewModel.konfirmasiPin.observe(this, Observer {
            if (it.length == pinView.itemCount) {
                val pinFromFirstActivity = sharedPreferences.getString("pin", "")
                if (it == pinFromFirstActivity) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    pinView.setLineColor(Color.RED)
                    pinErrorText.visibility = View.VISIBLE

                }
            }else {
                pinView.setLineColor(Color.parseColor("#6C63FF"))
                pinErrorText.visibility = View.GONE
            }
        })

        pinView.addTextChangedListener {
            it?.let {
                mpinViewModel.setKonfirmasiPin(it.toString())
            }
        }

    }
}