package com.digimbanking.Features.Auth.CreateRekening.Cif

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.core.domain.model.NikModel
import com.core.domain.model.PekerjaanItemModel
import com.digimbanking.databinding.ActivityBuatAkunBinding

class BuatAkun : AppCompatActivity(), BottomSheetPekerjaan.PekerjaanListener {
    lateinit var binding: ActivityBuatAkunBinding
    private val sharedViewModel: CifViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<NikModel>("dataNik")

        val intent = intent
        val pekerjaanValue = intent.getStringExtra("pekerjaan")

        if (pekerjaanValue != null) {
            binding.etPekerjaan.editText?.setText(pekerjaanValue)
        }

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
        }
    }
    override fun onPekerjaanSelected(selectedPekerjaan: PekerjaanItemModel) {
        binding.etPekerjaan.editText?.setText(selectedPekerjaan.nama)
    }
    override fun onResume() {
        super.onResume()
        sharedViewModel.selectedPekerjaan.observe(this, Observer { selectedPekerjaan ->
            if (selectedPekerjaan != null) {
                binding.etPekerjaan.editText?.setText(selectedPekerjaan.nama)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val selectedPekerjaan = data?.getParcelableExtra<PekerjaanItemModel>("pekerjaan")
            if (selectedPekerjaan != null) {
                binding.etPekerjaan.editText?.setText(selectedPekerjaan.nama)
            }
        }
    }
}