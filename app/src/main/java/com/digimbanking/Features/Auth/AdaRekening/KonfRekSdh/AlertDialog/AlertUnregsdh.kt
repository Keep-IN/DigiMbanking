package com.digimbanking.Features.Auth.AdaRekening.KonfRekSdh.AlertDialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.digimbanking.R
import com.digimbanking.databinding.ActivityBuatMpinsdhBinding
import com.digimbanking.databinding.AlertUnregsdhBinding

class AlertUnregsdh : DialogFragment() {
    private lateinit var binding: AlertUnregsdhBinding

    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertUnregsdhBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKembaliRek.setOnClickListener{
            dialog?.cancel()
        }
    }
}