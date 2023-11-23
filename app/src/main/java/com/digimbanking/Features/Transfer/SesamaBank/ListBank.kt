package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.response.listbank.DataBank
import com.digimbanking.Data.Adapter.BankListAdapter
import com.digimbanking.R
import com.digimbanking.databinding.ActivityListBankBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListBank : AppCompatActivity() {
    private lateinit var binding: ActivityListBankBinding
    private val adapterListBank: BankListAdapter by lazy { BankListAdapter() }
    private lateinit var viewModel: ListBankViewModel
    private lateinit var dataBank: MutableList<DataBank>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListBankBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListBankViewModel::class.java]
        binding.rvListBank.adapter = adapterListBank
        binding.rvListBank.layoutManager = LinearLayoutManager(this)

        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.getListBank().observe(this@ListBank){
                when(it){
                    is Result.Success -> {
                        Log.d("Data Content: ", "${it.data}")
                        adapterListBank.submitList(it.data.data)
                        dataBank = it.data.data.toMutableList()
                        adapterListBank.setOnclickItem(rvClickListener)
                    }
                    is Result.Error -> {
                        Toast.makeText(this@ListBank, it.errorMessage, Toast.LENGTH_SHORT).show()
                        Log.d("Tes", it.errorMessage)
                    }
                    else -> {
                        Log.d("Tes", "Empty JSON")
                    }
                }
            }
        }

        binding.tilSearch.editText?.doOnTextChanged { text, start, before, count ->
            val filteredList = dataBank.filter {
                it.namaBank.lowercase().contains(text.toString().lowercase())
            }
            adapterListBank.submitList(filteredList)
        }

        binding.tvCancel.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private val rvClickListener: (DataBank) -> Unit =
        { item ->
            startActivity(Intent(this, RekTujuan::class.java).apply {
                putExtra("bank", item)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                finish()
            })
        }
}