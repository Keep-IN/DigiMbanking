package com.digimbanking.Features.Auth.CreateRekening.Mpin.AlertMpin

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.AlertOtp.OtpFailed
import com.digimbanking.R
import com.digimbanking.databinding.FragmentMpinFailedBinding

class MpinFailed : DialogFragment() {
    private lateinit var binding : FragmentMpinFailedBinding

    companion object{
        fun newInstance(massage: String) : MpinFailed {
            val fragment= MpinFailed()
            val args = Bundle()
            args.apply {
                putString("message", massage)
                fragment.arguments = args
                return fragment
            }
        }
    }

    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMpinFailedBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val massage = arguments?.getString("message")
        binding.apply {
            txtError.text = massage
            btnKembali.setOnClickListener{
                dialog?.cancel()
            }
        }
    }
}