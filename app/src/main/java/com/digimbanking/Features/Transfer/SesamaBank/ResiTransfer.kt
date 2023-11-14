package com.digimbanking.Features.Transfer.SesamaBank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digimbanking.R
import com.digimbanking.databinding.ActivityResiTransferBinding

class ResiTransfer : AppCompatActivity() {
    private lateinit var binding: ActivityResiTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}