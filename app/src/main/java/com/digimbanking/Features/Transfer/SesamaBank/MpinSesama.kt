package com.digimbanking.Features.Transfer.SesamaBank

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.akun.DataRekeningAkun
import com.core.data.response.transferSesama.TransactionModel
import com.digimbanking.Features.Transfer.SesamaBank.AlertDialog.AlertDialogTerblokir
import com.digimbanking.databinding.ActivityMpinSesamaBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MpinSesama: AppCompatActivity() {
    private lateinit var binding: ActivityMpinSesamaBinding
    private lateinit var viewModel: MpinTransferViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMpinSesamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataTujuan = intent.getParcelableExtra<TransactionModel>("data")
        val dataRekening = intent.getParcelableExtra<DataRekeningAkun>("akun")
        var errorCount = 0
        viewModel = ViewModelProvider(this)[MpinTransferViewModel::class.java]
        binding.pvMpin.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    onLoading()
                    if (dataTujuan != null && dataRekening != null) {
                        viewModel.doTransaction(text.toString(), dataTujuan.catatan, dataRekening.rekening.joinToString { it.noRekening },dataTujuan.noRekeningTujuan, dataTujuan.nominal)
                            .observe(this@MpinSesama){
                                when(it){
                                    is Result.Success -> {
                                        Log.d("Isi Response", "${it.data}")
                                        binding.tvMpinErrorMsg.visibility = View.GONE
                                        startActivity(Intent(this@MpinSesama, ResiTransfer::class.java).apply {
                                            putExtra("dataResi", it.data)
                                        })
                                        onFinishedLoading()
                                    }

                                    is Result.Error -> {
                                        Toast.makeText(this@MpinSesama, it.errorMessage, Toast.LENGTH_SHORT).show()
                                        errorCount += 1
                                        binding.pvMpin.setLineColor(Color.RED)
                                        binding.pvMpin.setTextColor(Color.RED)
                                        binding.tvMpinErrorMsg.visibility = View.VISIBLE
                                        binding.tvMpinErrorMsg.text = it.errorMessage
                                        binding.pvMpin.text = null
                                        if(errorCount == 3){
                                            val alert = AlertDialogTerblokir.newInstance(it.errorMessage)
                                            alert.show(supportFragmentManager, "Alert terblokir")
                                        }
                                        Log.d("Tes", it.errorMessage)
                                        onFinishedLoading()
                                    }

                                    else -> {
                                        Log.d("Tes", "Empty JSON")
                                        onLoading()
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun onLoading(){
        binding.loadScreen.visibility = View.VISIBLE
        binding.loadScreen.setOnTouchListener { _, _ ->
            true
        }
    }

    private fun onFinishedLoading(){
        binding.loadScreen.visibility = View.GONE
    }
}