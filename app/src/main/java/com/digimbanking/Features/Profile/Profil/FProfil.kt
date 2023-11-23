package com.digimbanking.Features.Profile.Profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.domain.model.DataProfil
import com.core.domain.model.ProfilModel
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogFailLogin
import com.digimbanking.Features.Auth.Login.AlertDialog.AlertDialogSuccessLogin
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
        var nama = binding.tvNama.text.toString()
        var email = binding.tvProfEmail.text.toString()
        nama = if(nama.length > 20 ){
            nama.substring(0,20) + "..."
        } else {
            nama
        }
        email = if(email.length > 20){
            email.substring(0, 20) + "..."
        } else {
            email
        }

        binding.tvNama.text = nama
        binding.tvProfEmail.text = email

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