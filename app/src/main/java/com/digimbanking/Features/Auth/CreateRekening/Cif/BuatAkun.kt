package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.auth.createRekening.dukcapil.DukcapilResponse
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Features.Auth.CreateRekening.Card.NomorRekening
import com.digimbanking.databinding.ActivityBuatAkunBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuatAkun : AppCompatActivity(), BottomSheetPenghasilan.PenghasilanListener{
    lateinit var binding: ActivityBuatAkunBinding
    lateinit var cifViewModel: CifViewModel
    private val sharedPrefname = "card"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DukcapilResponse>("nik")

        cifViewModel = ViewModelProvider(this).get(CifViewModel::class.java)
        binding.btnLanjut.setOnClickListener {
            cifViewModel.viewModelScope.launch(Dispatchers.Main) {
                if (data != null) {
                    cifViewModel.sentCif(
                        data.data.alamat,
                        data.data.nama,
                        data.data.nik,
                        data.data.pekerjaan,
                        binding.etPenghasilan.editText?.text.toString())
                        .observe(this@BuatAkun) {
                            when(it) {
                                is Result.Success -> {
                                    it.data
                                    Log.d("Tes", "${it.data}")
                                    startActivity(Intent(this@BuatAkun, NomorRekening::class.java).apply {
                                        putExtra("nik", it.data)
                                    })
                                    onFinishedLoading()
                                }

                                is Result.Error -> {
                                    Toast.makeText(
                                        this@BuatAkun,
                                        "${it.errorMessage}",
                                        Toast.LENGTH_SHORT
                                    ).show()
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

        if (data != null) {
            binding.etAlamat.editText?.setText(data.data.alamat)
            binding.etNama.editText?.setText(data.data.nama)
            binding.etNIK.editText?.setText(data.data.nik)
            binding.etPekerjaan.editText?.setText(data.data.pekerjaan)
        }

        binding.apply {
            etPenghasilan.editText?.setOnClickListener {
                val bottomSheetPenghasilan = BottomSheetPenghasilan()
                bottomSheetPenghasilan.penghasilanListener = this@BuatAkun
                bottomSheetPenghasilan.show(supportFragmentManager, "penghasilan")
            }
        }

        binding.btnKembali.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onPenghasilanSelected(selectedPenghasilan: PenghasilanItemModel) {
        binding.etPenghasilan.editText?.setText(selectedPenghasilan.nama)
        saveSelectedPenghasilan(selectedPenghasilan)
        checkButtonStatus()
    }

    private fun saveSelectedPenghasilan(selectedPenghasilan: PenghasilanItemModel) {
        val sharedPreferences = getSharedPreferences("nama_file_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("penghasilanId", selectedPenghasilan.id)
        editor.putString("penghasilanNama", selectedPenghasilan.nama)
        editor.apply()
    }

    private fun getSavedPenghasilan(): PenghasilanItemModel? {
        val sharedPreferences = getSharedPreferences("nama_file_preferences", Context.MODE_PRIVATE)
        val penghasilanId = sharedPreferences.getInt("penghasilanId", -1)
        val penghasilanNama = sharedPreferences.getString("penghasilanNama", "")

        return if (penghasilanId != -1 && penghasilanNama?.isNotBlank() == true) {
            PenghasilanItemModel(penghasilanId, penghasilanNama)
        } else {
            null
        }
    }

    private fun checkButtonStatus() {
        val penghasilan = getSavedPenghasilan()
        val isPenghasilanSelected = penghasilan != null

        binding.btnLanjut.isEnabled = isPenghasilanSelected
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