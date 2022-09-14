package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutMe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutme)
        
        supportActionBar?.title  = "About Me"
    }
}