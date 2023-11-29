package com.digimbanking.Features.Auth.Question

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.digimbanking.Features.Auth.CreateRekening.Card.PilihKartu
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.databinding.BottomSheetSudahPunyaAkunBinding
import dagger.hilt.android.AndroidEntryPoint


class BottomSheetSudahPunyaAkun : SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetSudahPunyaAkunBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetSudahPunyaAkunBinding.inflate(layoutInflater, container, false)

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSudahPunyaRekening.setOnClickListener {
            startActivity(Intent(activity, Login::class.java))
            requireActivity().finishAffinity()
        }

        binding.ivXDaftarRek.setOnClickListener {
            dialog?.cancel()
        }

        binding.btnDaftarRekening.setOnClickListener {
            startActivity(Intent(activity, PilihKartu::class.java))
        }
    }

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}