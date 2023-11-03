package com.digimbanking.Features.Transfer.TransferSesama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityListBankBinding

class ListBank : AppCompatActivity() {
    private lateinit var binding: ActivityListBankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListBankBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}