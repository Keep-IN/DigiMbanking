package com.digimbanking.Features.Auth.CreateRekening.Mpin

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.chaos.view.PinView
import com.core.data.network.Result
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.R
import com.digimbanking.databinding.ActivityKonfirmasiMpinBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        binding.konfMpin.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6) {
                mpinViewModel.viewModelScope.launch(Dispatchers.Main) {
                    mpinViewModel.konfirmasiPin.observe(this@KonfirmasiMpin, Observer {
                        if (it.length == pinView.itemCount) {
                            val pinFromFirstActivity = sharedPreferences.getString("pin", "")
                            if (it == pinFromFirstActivity) {
                                val intent = Intent(this@KonfirmasiMpin, Login::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                pinView.setLineColor(Color.RED)
                                pinErrorText.visibility = View.VISIBLE
                            }
                        } else {
                            pinView.setLineColor(Color.parseColor("#6C63FF"))
                            pinErrorText.visibility = View.GONE
                        }
                    })
                    mpinViewModel.putMpin(text.toString())
                        .observe(this@KonfirmasiMpin, Observer { result ->
                            when (result) {
                                is Result.Error -> {
                                    Toast.makeText(
                                        this@KonfirmasiMpin,
                                        result.errorMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                is Result.Loading -> {

                                }

                                else -> {

                                }
                            }
                        })
                }
            }
        }

        pinView.addTextChangedListener {
            it?.let {
                mpinViewModel.setKonfirmasiPin(it.toString())
            }
        }

    }
}