package com.digimbanking.Features.Auth.OnBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.digimbanking.databinding.ActivitySplashBinding
import com.github.ybq.android.spinkit.BuildConfig

class Splash : AppCompatActivity() {
    private val splashTimeOut: Long = 3000
    private  lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val versionName = "Version " + BuildConfig.VERSION_NAME
        binding.tvVersion.text = versionName

        Log.d("SplashActivity", "Version Name: $versionName")

        Handler().postDelayed({
            val intent = Intent(this@Splash, Onboard::class.java)
            startActivity(intent)

            finish()
        }, splashTimeOut)
    }
}