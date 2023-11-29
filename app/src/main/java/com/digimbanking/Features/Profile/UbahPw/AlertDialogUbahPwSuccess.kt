package com.digimbanking.Features.Profile.UbahPw

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.Features.Profile.Profil.FProfil
import com.digimbanking.R
import com.digimbanking.databinding.AlertDialogUbahPwSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlertDialogUbahPwSuccess : DialogFragment() {
    lateinit var binding: AlertDialogUbahPwSuccessBinding
    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =AlertDialogUbahPwSuccessBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOkSusesUbahPw.setOnClickListener {
            startActivity(Intent(activity, NavbarContainer::class.java))
            requireActivity().finishAffinity()
        }
    }
}