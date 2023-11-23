package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.core.data.response.auth.createRekening.dukcapil.DukcapilResponse
import com.core.domain.model.NikModel
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Features.Auth.CreateRekening.Card.NomorRekening
import com.digimbanking.databinding.ActivityBuatAkunBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuatAkun : AppCompatActivity(), BottomSheetPenghasilan.PenghasilanListener{
    lateinit var binding: ActivityBuatAkunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DukcapilResponse>("nik")

        if (data != null) {
            binding.etNIK.editText?.setText(data.data.nik)
            binding.etNama.editText?.setText(data.data.nama)
            binding.etAlamat.editText?.setText(data.data.alamat)
            binding.etPekerjaan.editText?.setText(data.data.pekerjaan)
        }

        binding.apply {
            etPenghasilan.editText?.setOnClickListener {
                val bottomSheetPenghasilan = BottomSheetPenghasilan()
                bottomSheetPenghasilan.penghasilanListener = this@BuatAkun
                bottomSheetPenghasilan.show(supportFragmentManager, "penghasilan")
            }

            btnLanjut.setOnClickListener {
                if (data != null) {
                    startActivity(Intent(this@BuatAkun, NomorRekening::class.java).apply {
                        putExtra("dataNik", data)
                    })
                } else {
                    Toast.makeText(this@BuatAkun, "Nik tidak terdaftar", Toast.LENGTH_SHORT).show()
                }
            }
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

    override fun onDestroy() {
        super.onDestroy()
        clearPreferences()
    }

    private fun clearPreferences() {

        val penghasilanPreferences = getSharedPreferences("nama_file_preferences", Context.MODE_PRIVATE)
        penghasilanPreferences.edit().clear().apply()
    }
}