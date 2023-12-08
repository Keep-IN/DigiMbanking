package com.digimbanking.Features.Auth.CreateRekening.Mpin.AlertMpin

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.digimbanking.Features.Auth.CreateRekening.Mpin.BuatMpin
import com.digimbanking.Features.Auth.CreateRekening.Registrasi.AlertOtp.OtpFailed
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.R
import com.digimbanking.databinding.FragmentMpinSuccessBinding

class MpinSuccess : DialogFragment() {
    private lateinit var binding : FragmentMpinSuccessBinding

    companion object{
        fun newInstance(massage: String) : MpinSuccess {
            val fragment= MpinSuccess()
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMpinSuccessBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val massage = arguments?.getString("massage")

        binding.apply {
            textView40.text = massage
        }

        binding.btnOkSukses.setOnClickListener{
            startActivity(Intent(activity, Login::class.java))
            requireActivity().finishAffinity()
        }
    }
}