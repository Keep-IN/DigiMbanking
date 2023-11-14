package com.digimbanking.Features.Profile.Profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogFailLogin
import com.digimbanking.Features.Onboard.MainActivity
import com.digimbanking.Features.Profile.Profil.AlertDialog.AlertDialogLogout
import com.digimbanking.Features.Profile.UbahPw.UbahPw
import com.digimbanking.R
import com.digimbanking.databinding.ActivityLoginBinding
import com.digimbanking.databinding.FragmentFProfilBinding

class FProfil : Fragment() {
    private lateinit var binding: FragmentFProfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFProfilBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply{
            cvUbahPw.setOnClickListener{
                startActivity(Intent(activity, UbahPw::class.java))
            }

            cvLogout.setOnClickListener {
                AlertDialogLogout().show(childFragmentManager, "test")
            }
        }
    }
}