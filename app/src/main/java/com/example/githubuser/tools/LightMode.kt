package com.example.githubuser.tools

import androidx.appcompat.app.AppCompatDelegate

object LightMode {
    fun getSetup(lmValue: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (lmValue) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        )
    }
}