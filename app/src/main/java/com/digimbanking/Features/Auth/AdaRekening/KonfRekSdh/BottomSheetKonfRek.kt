package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.BuatSandiSudah
import com.digimbanking.Features.Auth.AdaRekening.KonfEmailSdh.KonfirmasiEmailSudah
import com.digimbanking.R
import com.digimbanking.databinding.BottomSheetKonfRekBinding

class BottomSheetKonfRek(private val namaLengkap: String, private val nik: String) : SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetKonfRekBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetKonfRekBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvnamaRek.text = namaLengkap
        binding.tvnoRek.text = nik

        binding.button3.setOnClickListener {
            startActivity(Intent(activity, BuatSandiSudah::class.java))
            requireActivity().finishAffinity()
        }
    }

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}