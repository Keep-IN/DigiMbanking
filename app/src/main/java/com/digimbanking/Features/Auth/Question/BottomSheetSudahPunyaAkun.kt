package com.digimbanking.Features.Auth.Question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.digimbanking.databinding.BottomSheetSudahPunyaAkunBinding

class BottomSheetSudahPunyaAkun : SuperBottomSheetFragment() {
    lateinit var binding: BottomSheetSudahPunyaAkunBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BottomSheetSudahPunyaAkunBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun isSheetAlwaysExpanded() = true

    override fun getExpandedHeight() = -2
}