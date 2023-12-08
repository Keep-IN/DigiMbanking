package com.digimbanking.Features.Auth.AdaRekening.MpinSdh.KonfirmasiMPINsdh.AlertMPIN

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import com.digimbanking.R
import com.digimbanking.databinding.AlertMpinDecBinding

class ALERTMPINDEC : DialogFragment() {
    lateinit var binding: AlertMpinDecBinding
    private var errorMessage: String = ""

    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }
    fun setErrorMessage(message: String) {
        errorMessage = message
        binding.tvErrormsgMpin.text = errorMessage
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertMpinDecBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnOkSuksesLogin.setOnClickListener{
//            startActivity(Intent(activity, NavbarContainer::class.java))
//            requireActivity().finishAffinity()
//        }
    }
}