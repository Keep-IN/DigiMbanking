package com.digimbanking.Features.Transfer.Riwayat.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.digimbanking.R
import com.digimbanking.databinding.ActivityNavbarContainerBinding

class NavbarContainer : AppCompatActivity() {
    private lateinit var binding: ActivityNavbarContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_container)
        replaceFragment(BerandaFragment())

        binding.bottomNavigationView.selectedItemId = R.id.nav_beranda
        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_beranda -> replaceFragment(BerandaFragment())
                R.id.nav_akun -> replaceFragment(AkunFragment())
                R.id.nav_riwayat -> replaceFragment(RiwayatFragment())
                R.id.nav_profil -> replaceFragment(ProfilFragment())
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

    override fun onBackPressed() {
        BottomSheetKeluar().show(supportFragmentManager, "Keluar")
    }
}