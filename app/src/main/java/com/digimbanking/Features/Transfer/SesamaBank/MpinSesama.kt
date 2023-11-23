package com.digimbanking.Features.Transfer.SesamaBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
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

        viewModel = ViewModelProvider(this)[MpinViewModel::class.java]
        binding.pvMpin.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.viewModelScope.launch(Dispatchers.Main) {
                        viewModel.doTransaction("898725", "tes API", "7727272726678","7727272726677", 20000)
                            .observe(this@MpinSesama){
                                when(it){
                                    is Result.Success -> {
                                        it.data
                                        Log.d("Tes", "${it.data}")
                                        startActivity(Intent(this@MpinSesama, ResiTransfer::class.java))
                                    }
                                    is Result.Error -> {
                                        Toast.makeText(this@MpinSesama, it.errorMessage, Toast.LENGTH_SHORT).show()
                                        Log.d("Tes", it.errorMessage)
                                    }
                                    else -> {
                                        Log.d("Tes", "Empty JSON")
                                    }
                                }
                            }
                    }
                    binding.pvMpin.text = null
                }, 1500)
            }
        }
    }
}