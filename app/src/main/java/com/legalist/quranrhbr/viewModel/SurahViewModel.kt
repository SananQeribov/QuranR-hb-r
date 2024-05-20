package com.legalist.quranrhbr.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ders.domain.model.Surah
import com.legalist.mylibrary.managers.api.ApiClient
import com.legalist.mylibrary.managers.repository.SurahRepository
import kotlinx.coroutines.launch

// domain/SurahViewModel.kt
class SurahViewModel : ViewModel() {
    private val apiService = ApiClient.getApiService()
    private val repository = SurahRepository(apiService)

    private val _surahs = MutableLiveData<List<Surah>>()
    val surahs: LiveData<List<Surah>> get() = _surahs

    init {
        fetchSurahs()
    }

    private fun fetchSurahs() {
        viewModelScope.launch {
            _surahs.value = repository.getSurahs()
        }
    }
    //adkahdkajdhkajhdkad
}
