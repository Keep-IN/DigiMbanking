package com.digimbanking.Features.Dashboard.BottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.digimbanking.Features.Auth.Login.Login
import com.digimbanking.databinding.BottomSheetLogoutBinding

class BottomSheetLogout: SuperBottomSheetFragment() {
    private lateinit var binding: BottomSheetLogoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetLogoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogOut.setOnClickListener {
            startActivity(Intent(activity, Login::class.java))
            requireActivity().finishAffinity()
        }
    }
    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}