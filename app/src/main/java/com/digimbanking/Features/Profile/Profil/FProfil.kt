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
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.marginRight
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.digimbanking.Features.Profile.Profil.AlertDialog.AlertDialogLogout
import com.digimbanking.Features.Profile.UbahPw.UbahPw
import com.digimbanking.R
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
        profilViewModel.viewModelScope.launch(Dispatchers.Main){
            onLoading()
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

                        when(binding.tvGold.text){
                            "Silver" -> {
                                binding.clCard.setBackgroundResource(R.drawable.card_silver)
                            }
                            "Gold" -> {
                                binding.clCard.setBackgroundResource(R.drawable.card_gold)
                            }
                            "Platinum" -> {
                                binding.clCard.setBackgroundResource(R.drawable.card_platinum)
                            }
                        }
                        onFinishedLoading()
                    }
                    is Result.Error -> {
                        Log.d("error get", result.errorMessage)
                        onFinishedLoading()
                    } else -> {
                        Log.d("Unexpected Error", "eror nyuk")
                        onLoading()
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



    private fun onLoading(){
        binding.flLoading.visibility = View.VISIBLE
    }

    private fun  onFinishedLoading(){
        binding.flLoading.visibility = View.GONE
    }
}