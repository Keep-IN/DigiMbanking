package com.digimbanking.Features.Auth.Login.AlertDialog

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
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.databinding.AlertDialogSuccessLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlertDialogSuccessLogin : DialogFragment() {
    private lateinit var binding: AlertDialogSuccessLoginBinding

    companion object{
        fun newInstance(massage : String): AlertDialogSuccessLogin {
            val fragment = AlertDialogSuccessLogin()
            val args = Bundle()
            args.apply {
                putString("massage", massage)
                fragment.arguments = args
                return fragment
            }
        }
    }

    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertDialogSuccessLoginBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOkSuksesLogin.setOnClickListener{
            startActivity(Intent(activity, NavbarContainer::class.java))
            requireActivity().finishAffinity()
        }
    }
}