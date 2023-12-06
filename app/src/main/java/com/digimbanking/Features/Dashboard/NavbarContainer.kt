package com.digimbanking.Features.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.digimbanking.Features.Akun.AkunFragment
import com.digimbanking.Features.Profile.Profil.FProfil
import com.digimbanking.Features.Transfer.Riwayat.Mutasi.RiwayatFragment
import com.digimbanking.R
import com.digimbanking.databinding.ActivityNavbarContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavbarContainer : AppCompatActivity() {
    private lateinit var binding: ActivityNavbarContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavbarContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(BerandaFragment())

        binding.bottomNavigationView.selectedItemId = R.id.nav_beranda
        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_beranda -> replaceFragment(BerandaFragment())
                R.id.nav_akun -> replaceFragment(AkunFragment())
                R.id.nav_riwayat -> replaceFragment(RiwayatFragment())
                R.id.nav_profil -> replaceFragment(FProfil())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.fragContainer, fragment)
            commit()
        }
    }
}