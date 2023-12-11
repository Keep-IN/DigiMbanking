package com.digimbanking.Features.Auth.CreateRekening.Registrasi.AlertPassword

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
import com.digimbanking.Features.Auth.CreateRekening.Cif.Nik
import com.digimbanking.Features.Auth.CreateRekening.Mpin.BuatMpin
import com.digimbanking.R
import com.digimbanking.databinding.ActivityPasswordSuccessBinding
import com.digimbanking.databinding.AlertSandiAccBinding

class PasswordSuccess : DialogFragment() {
    private lateinit var binding: ActivityPasswordSuccessBinding

    companion object {
        fun newInstance(massage : String) : PasswordSuccess {
            val fragment = PasswordSuccess()
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityPasswordSuccessBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val massage = arguments?.getString("message")
        binding.apply {
            txtSandi.text = massage
        }

        binding.btnOkSukses.setOnClickListener {
            startActivity(Intent(activity, BuatMpin::class.java))
            requireActivity().finishAffinity()
        }

    }
}