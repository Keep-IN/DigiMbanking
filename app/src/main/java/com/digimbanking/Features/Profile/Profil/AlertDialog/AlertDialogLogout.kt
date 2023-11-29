package com.digimbanking.Features.Profile.Profil.AlertDialog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.Features.Profile.Profil.FProfil
import com.digimbanking.R
import com.digimbanking.databinding.AlertDialogLogoutBinding
import com.digimbanking.databinding.AlertDialogUbahPwSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlertDialogLogout : DialogFragment() {
    lateinit var binding: AlertDialogLogoutBinding
    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertDialogLogoutBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBatalLogout.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnOkLogout.setOnClickListener{
            startActivity(Intent(activity, Login::class.java))
        }
    }
}