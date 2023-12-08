package com.digimbanking.Features.Transfer.TransferSesama.AlertDialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.digimbanking.Features.Transfer.SesamaBank.BottomSheet.BottomSheetDetailPenerima
import com.digimbanking.databinding.AlertDialogRekeningErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlertDialogGagal: DialogFragment() {
    private lateinit var binding: AlertDialogRekeningErrorBinding

    companion object{
        fun newInstance(message: String): AlertDialogGagal {
            val fragment = AlertDialogGagal()
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
        binding = AlertDialogRekeningErrorBinding.inflate(layoutInflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val error = arguments?.getString("error")
        binding.apply {
            tvErrorMessage.text = error
            btnCancelAlert.setOnClickListener {
                dialog?.cancel()
            }
        }
    }
}