package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.core.domain.model.NikModel
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Features.Auth.CreateRekening.Card.NomorRekening
import com.digimbanking.databinding.ActivityBuatAkunBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuatAkun : AppCompatActivity(), BottomSheetPekerjaan.PekerjaanListener, BottomSheetPenghasilan.PenghasilanListener{
    lateinit var binding: ActivityBuatAkunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<NikModel>("dataNik")

        if (data != null) {
            binding.etNIK.editText?.setText(data.nik)
            binding.etNama.editText?.setText(data.nama)
            binding.etAlamat.editText?.setText(data.alamat)
        }

        binding.apply {
            etPekerjaan.editText?.setOnClickListener {
                val bottomSheetPekerjaan = BottomSheetPekerjaan()
                bottomSheetPekerjaan.pekerjaanListener = this@BuatAkun
                bottomSheetPekerjaan.show(supportFragmentManager, "pekerjaan")
            }

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

    override fun onPekerjaanSelected(selectedPekerjaan: PekerjaanItemModel) {
        binding.etPekerjaan.editText?.setText(selectedPekerjaan.nama)
        saveSelectedPekerjaan(selectedPekerjaan)
        checkButtonStatus()
    }

    override fun onPenghasilanSelected(selectedPenghasilan: PenghasilanItemModel) {
        binding.etPenghasilan.editText?.setText(selectedPenghasilan.nama)
        saveSelectedPenghasilan(selectedPenghasilan)
        checkButtonStatus()
    }

    private fun saveSelectedPekerjaan(selectedPekerjaan: PekerjaanItemModel) {
        val sharedPreferences = getSharedPreferences("job", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("pekerjaanId", selectedPekerjaan.id)
        editor.putString("pekerjaanNama", selectedPekerjaan.nama)
        editor.apply()
    }

    private fun getSavedPekerjaan(): PekerjaanItemModel? {
        val sharedPreferences = getSharedPreferences("job", Context.MODE_PRIVATE)
        val pekerjaanId = sharedPreferences.getInt("pekerjaanId", -1)
        val pekerjaanNama = sharedPreferences.getString("pekerjaanNama", "")

        return if (pekerjaanId != -1 && pekerjaanNama?.isNotBlank() == true) {
            PekerjaanItemModel(pekerjaanId, pekerjaanNama)
        } else {
            null
        }
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
        val pekerjaan = getSavedPekerjaan()
        val penghasilan = getSavedPenghasilan()

        val isPekerjaanSelected = pekerjaan != null
        val isPenghasilanSelected = penghasilan != null

        binding.btnLanjut.isEnabled = isPekerjaanSelected && isPenghasilanSelected
    }

    override fun onDestroy() {
        super.onDestroy()
        clearPreferences()
    }

    private fun clearPreferences() {
        val jobPreferences = getSharedPreferences("job", Context.MODE_PRIVATE)
        jobPreferences.edit().clear().apply()

        val penghasilanPreferences = getSharedPreferences("nama_file_preferences", Context.MODE_PRIVATE)
        penghasilanPreferences.edit().clear().apply()
    }
}