package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.digimbanking.R
import com.digimbanking.databinding.BottomSheetKonfRekBinding

class BottomSheetKonfRek : SuperBottomSheetFragment() {
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

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}