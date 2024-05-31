package com.legalist.mylibrary.managers.db
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ders.domain.model.Surah

class SharedPreferencesManager(private val sharedPreferences: SharedPreferences) {

    fun saveSurahs(surahs: List<Surah>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(surahs)
        editor.putString("surahs", json)
        editor.apply()
    }

    fun loadSurahs(): List<Surah>? {
        val gson = Gson()
        val json = sharedPreferences.getString("surahs", null)
        return json?.let {
            gson.fromJson(it, Array<Surah>::class.java).toList()
        }
    }

    fun clearSurahs() {
        sharedPreferences.edit().remove("surahs").apply()
    }
}
