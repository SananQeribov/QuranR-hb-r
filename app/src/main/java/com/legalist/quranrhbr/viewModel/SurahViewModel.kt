package com.legalist.quranrhbr.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ders.domain.model.Surah
import com.legalist.mylibrary.managers.local.room.db.ZikrDatabase
import com.legalist.mylibrary.managers.remote.api.ApiClient

import com.legalist.mylibrary.managers.repository.SurahRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SurahViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val apiService = ApiClient.getApiService()
    private val repository = SurahRepository(apiService, sharedPreferences)

    private val _surahs = MutableStateFlow<List<Surah>?>(null)
    val surahs: StateFlow<List<Surah>?> get() = _surahs

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        fetchSurahs()
    }
//merged
    private fun fetchSurahs() {
        viewModelScope.launch {
            try {
                val surahsList = repository.getSurahs()
                if (surahsList != null) {
                    _surahs.value = surahsList
                } else {
                    _error.value = "No data available"
                }
            } catch (e: Exception) {
                _error.value = "Failed to fetch data: ${e.message}"
            }
        }
    }
}
