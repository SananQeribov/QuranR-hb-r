package com.legalist.quranrhbr.Application

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class MyApplication : Application() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val isDarkMode = preferences.getBoolean("dark_mode", false)
        setAppTheme(isDarkMode)
    }

    fun toggleDarkMode() {
        val isDarkMode = preferences.getBoolean("dark_mode", false)
        val editor = preferences.edit()
        editor.putBoolean("dark_mode", !isDarkMode)
        editor.apply()
        setAppTheme(!isDarkMode)
    }

    private fun setAppTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
