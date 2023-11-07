package com.digimbanking.Features.Transfer.SesamaBank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityInputNominalBinding

class InputNominal : AppCompatActivity() {
    private lateinit var binding: ActivityInputNominalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNominalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}