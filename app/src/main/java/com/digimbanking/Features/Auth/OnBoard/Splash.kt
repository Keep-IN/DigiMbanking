package com.digimbanking.Features.Auth.OnBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.Data
import com.core.domain.model.DataVersion
import com.digimbanking.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint



class Splash : AppCompatActivity() {
    private val splashTimeOut: Long = 3000
    private  lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({
            val intent = Intent(this@Splash, Onboard::class.java)
            startActivity(intent)

            finish()
        }, splashTimeOut)
    }
}