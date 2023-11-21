package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.digimbanking.R
import com.digimbanking.databinding.ActivityMpinSesamaBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@AndroidEntryPoint
class MpinSesama: AppCompatActivity() {
    private lateinit var binding: ActivityMpinSesamaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMpinSesamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pvMpin.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, ResiTransfer::class.java))
                    binding.pvMpin.text = null
                }, 1500)
            }
        }
    }
}