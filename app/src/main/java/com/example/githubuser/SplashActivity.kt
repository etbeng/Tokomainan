package com.example.githubuser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.githubuser.tools.LightMode

class SplashActivity : AppCompatActivity() {
    private val lama : Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    
        //cari default value light mode dan set
        val lmKey = resources.getString(R.string.lmKey)
        val sp = getSharedPreferences("com.example.githubuser", Context.MODE_PRIVATE)
        val lmValue = sp.getBoolean(lmKey, true)
        //run default light mode
        LightMode.getSetup(lmValue)
    
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, lama)
    }
}