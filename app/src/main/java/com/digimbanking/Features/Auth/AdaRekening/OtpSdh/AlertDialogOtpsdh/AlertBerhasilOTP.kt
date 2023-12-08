package com.digimbanking.Features.Auth.AdaRekening.OtpSdh.AlertDialogOtpsdh

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
import com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.KonfirmasiRekSudah
import com.digimbanking.R
import com.digimbanking.databinding.AlertBerhasilOtpBinding
import com.digimbanking.databinding.AlertMpinAccBinding

class AlertBerhasilOTP : DialogFragment(){
    lateinit var binding: AlertBerhasilOtpBinding

    companion object{
        fun newInstance(massage : String): AlertBerhasilOTP {
            val fragment = AlertBerhasilOTP()
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
        binding = AlertBerhasilOtpBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val massage = arguments?.getString("massage")

        binding.apply {
            tvSaldoSumber.text = massage
        }

        binding.btnSuksesOTP.setOnClickListener{
            startActivity(Intent(activity, KonfirmasiRekSudah::class.java))
            requireActivity().finishAffinity()
        }
    }

}