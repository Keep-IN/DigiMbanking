package com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.AlertMPIN

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
import com.digimbanking.Features.Auth.AdaRekening.BuatSandiSdh.AlertSandi.AlertSandiACC
import com.digimbanking.Features.Auth.AdaRekening.MpinSdh.BuatMPINsdh
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.R
import com.digimbanking.databinding.AlertDialogSuccessLoginBinding
import com.digimbanking.databinding.AlertMpinAccBinding

class AlertMPINACC : DialogFragment() {
    lateinit var binding: AlertMpinAccBinding

    companion object {
        fun newInstance(massage: String): AlertMPINACC {
            val fragment = AlertMPINACC()
            val args = Bundle()
            args.apply {
                putString("massage", massage)
                fragment.arguments = args
                return fragment
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertMpinAccBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val massage = arguments?.getString("massage")

        binding.apply {
            textView48.text = massage
        }

        binding.btnokMPIN.setOnClickListener {
            startActivity(Intent(activity, Login::class.java))
            requireActivity().finishAffinity()
        }
    }
}