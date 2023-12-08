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
import com.digimbanking.R
import com.digimbanking.databinding.ActivityPasswordFailedBinding
import com.digimbanking.databinding.ActivityPasswordSuccessBinding

class PasswordFailed : DialogFragment() {
    private lateinit var binding: ActivityPasswordFailedBinding

    companion object {
        fun newInstance(massage : String) : PasswordFailed {
            val fragment = PasswordFailed()
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
        binding = ActivityPasswordFailedBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val massage = arguments?.getString("massage")
//        binding.apply {
//            txtSandi.text = massage
//        }

        binding.btnKembali.setOnClickListener {
            dialog?.cancel()
        }

    }
}