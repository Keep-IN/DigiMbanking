package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.transferSesama.DataNasabahTujuan
import com.core.data.response.transferSesama.TransactionModel
import com.digimbanking.R
import com.digimbanking.databinding.ActivityMpinSesamaBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MpinSesama: AppCompatActivity() {
    private lateinit var binding: ActivityMpinSesamaBinding
    private lateinit var viewModel: MpinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMpinSesamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataTujuan = intent.getParcelableExtra<TransactionModel>("data")
        viewModel = ViewModelProvider(this)[MpinViewModel::class.java]
        binding.pvMpin.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    if (dataTujuan != null) {
                        viewModel.doTransaction(text.toString(), dataTujuan.catatan, "7727272726678",dataTujuan.noRekeningTujuan, dataTujuan.nominal)
                            .observe(this@MpinSesama){
                                when(it){
                                    is Result.Success -> {
                                        Log.d("Isi Response", "${it.data}")
                                        binding.tvMpinErrorMsg.visibility = View.GONE
                                        startActivity(Intent(this@MpinSesama, ResiTransfer::class.java).apply {
                                            putExtra("dataResi", it.data)
                                        })
                                    }

                                    is Result.Error -> {
                                        Toast.makeText(this@MpinSesama, it.errorMessage, Toast.LENGTH_SHORT).show()
                                        val errorCount = 0
                                        errorCount.plus(1)
                                        binding.pvMpin.setLineColor(Color.RED)
                                        binding.pvMpin.setTextColor(Color.RED)
                                        binding.tvMpinErrorMsg.visibility = View.VISIBLE
                                        binding.tvMpinErrorMsg.text = it.errorMessage
                                        binding.pvMpin.text = null
                                        Log.d("Tes", it.errorMessage)
                                    }

                                    else -> {
                                        Log.d("Tes", "Empty JSON")
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}