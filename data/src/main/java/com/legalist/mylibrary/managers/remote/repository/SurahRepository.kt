package com.legalist.mylibrary.managers.repository




import android.content.SharedPreferences
import com.ders.domain.model.Surah
import com.legalist.mylibrary.managers.api.ApiService
import com.google.gson.Gson

class SurahRepository(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun getSurahs(): List<Surah>? {
        val savedData = loadData()
        return if (savedData.isNullOrEmpty()) {
            val response = apiService.getSurahs()
            if (response.isSuccessful) {
                val surahs = response.body()?.data
                if (surahs != null) {
                    saveData(surahs)
                }
                surahs
            } else {
                null
            }
        } else {
            savedData
        }
    }

    private fun saveData(surahs: List<Surah>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(surahs)
        editor.putString("surahs", json)
        editor.apply()
    }

    private fun loadData(): List<Surah>? {
        val gson = Gson()
        val json = sharedPreferences.getString("surahs", null)
        return json?.let {
            gson.fromJson(it, Array<Surah>::class.java).toList()
        }
    }
}
