package com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.AlertSandi

import android.app.Dialog
import android.content.Intent

import androidx.fragment.app.DialogFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContentProviderCompat.requireContext
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.BuatMPINsdh
import com.digimbanking.R
import com.digimbanking.databinding.AlertSandiDecBinding
import com.digimbanking.databinding.AlertUnregsdhBinding
import dagger.hilt.android.AndroidEntryPoint


class AlertSandiDEC : DialogFragment() {
    private lateinit var binding: AlertSandiDecBinding
    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertSandiDecBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDecSandi.setOnClickListener {
            startActivity(Intent(activity, BuatMPINsdh::class.java))
            requireActivity().finishAffinity()
        }

    }
}