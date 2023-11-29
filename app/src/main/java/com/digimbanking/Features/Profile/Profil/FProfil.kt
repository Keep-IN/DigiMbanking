package com.digimbanking.Features.Profile.Profil

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.response.login.DataLoginResponse
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FProfil : Fragment() {
    private lateinit var binding: FragmentFProfilBinding
    private lateinit var profilViewModel: ProfilViewModel
    private lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilViewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

        binding = FragmentFProfilBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("token", Context.MODE_PRIVATE)
//        val token = sharedPref.getString("token", null)
//        if (token != null) {
//            Log.d("isi Token:", token)
//        }

        profilViewModel.viewModelScope.launch(Dispatchers.Main){
            val token = sharedPref.getString("token", "").toString()
            profilViewModel.getProfil()
                .observe(viewLifecycleOwner){result ->
                when(result){
                    is Result.Success -> {
                        val nama = if (result.data.data.name.length > 20) {
                            result.data.data.name.substring(0, 20) + "..."
                        } else {
                            result.data.data.name
                        }

                        val email = if (result.data.data.email.length > 20) {
                            result.data.data.email.substring(0, 20) + "..."
                        } else {
                            result.data.data.email
                        }

                        binding.apply{
                            tvNama.text = nama
                            tvIsianNorek.text = result.data.data.rekening.map { it.noRekening }.joinToString("" )
                            tvNik.text = result.data.data.nik
                            tvProfEmail.text = email
                            tvGold.text = result.data.data.rekening.map { it.tipeRekening.namaTipe }.joinToString("")
                        }
                    }
                    is Result.Error -> {
                        Log.d("error get", result.errorMessage)
                    } else -> {
                        Log.d("Unexpected Error", "eror nyuk")
                    }
                }
            }
        }


        binding.apply{
            cvUbahPw.setOnClickListener{
                startActivity(Intent(activity, UbahPw::class.java))
            }

            cvUbahMpin.setOnClickListener {
//                startActivity(Intent(activity, ubah mpin))
            }

            cvLogout.setOnClickListener {
                AlertDialogLogout().show(childFragmentManager, "test")
            }
        }
    }
}