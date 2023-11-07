package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.domain.model.BankItemModel
import com.core.domain.model.DataBank
import com.digimbanking.Data.Adapter.BankListAdapter
import com.digimbanking.R
import com.digimbanking.databinding.ActivityListBankBinding

class ListBank : AppCompatActivity() {
    private lateinit var binding: ActivityListBankBinding
    private val adapterListBank: BankListAdapter by lazy { BankListAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListBankBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvListBank.adapter = adapterListBank
        binding.rvListBank.layoutManager = LinearLayoutManager(this)
        adapterListBank.submitList(DataBank.listBank)
        adapterListBank.setOnclickItem(rvClickListener)

        binding.tilSearch.editText?.doOnTextChanged { text, start, before, count ->
            val filteredList = DataBank.listBank.filter {
                it.nama.lowercase().contains(text.toString().lowercase())
            }
            adapterListBank.submitList(filteredList)
        }

        binding.tvCancel.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private val rvClickListener: (BankItemModel) -> Unit =
        { item ->
            startActivity(Intent(this, RekTujuan::class.java).apply {
                putExtra("bank", item)
            })
        }
}