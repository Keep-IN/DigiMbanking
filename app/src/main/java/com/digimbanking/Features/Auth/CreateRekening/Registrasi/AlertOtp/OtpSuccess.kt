package com.digimbanking.Features.Auth.CreateRekening.Registrasi.AlertOtp

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
import com.digimbanking.Features.Auth.CreateRekening.Cif.Nik
import com.digimbanking.Features.Auth.CreateRekening.Mpin.BuatMpin
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.R
import com.digimbanking.databinding.FragmentOtpSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpSuccess : DialogFragment() {
    private lateinit var binding: FragmentOtpSuccessBinding

    companion object{
        fun newInstance(massage : String): OtpSuccess {
            val fragment = OtpSuccess()
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
        // Inflate the layout for this fragment
        binding = FragmentOtpSuccessBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val massage = arguments?.getString("message")

        binding.apply {
            textView40.text = massage
        }

        binding.btnOkSukses.setOnClickListener{
            startActivity(Intent(activity, Nik::class.java))
            requireActivity().finishAffinity()
        }
    }

}