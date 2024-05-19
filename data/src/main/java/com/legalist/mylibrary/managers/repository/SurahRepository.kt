package com.legalist.mylibrary.managers.repository

import androidx.lifecycle.LiveData
import com.ders.domain.model.Surah
import com.legalist.mylibrary.managers.api.ApiService

// domain/SurahRepository.kt
class SurahRepository(private val apiService: ApiService) {
    suspend fun getSurahs(): List<Surah>? {
        val response = apiService.getSurahs()
        return if (response.isSuccessful) {
            response.body()?.data
        } else {
            null
        }
    }
}
