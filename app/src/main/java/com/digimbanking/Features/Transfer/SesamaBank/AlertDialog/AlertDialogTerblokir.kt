package com.digimbanking.Features.Transfer.SesamaBank.AlertDialog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.DialogFragment
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.Features.Transfer.TransferSesama.AlertDialog.AlertDialogGagal
import com.digimbanking.databinding.AlertDialogAkunTerblokirBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlertDialogTerblokir: DialogFragment() {
    private lateinit var binding: AlertDialogAkunTerblokirBinding

    companion object{
        fun newInstance(message: String): AlertDialogTerblokir {
            val fragment = AlertDialogTerblokir()
            val args = Bundle()
            args.apply{
                putString("error", message)
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
    ): View {
        binding = AlertDialogAkunTerblokirBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val error = arguments?.getString("error")
        binding.apply {
            tvErrorMessage.text = error
            btnCancelAlert.setOnClickListener {
                startActivity(Intent(activity, Login::class.java))
                activity?.finishAffinity()
            }
        }
    }
}