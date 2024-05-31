package com.legalist.quranrhbr.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legalist.mylibrary.managers.repository.QuranRepository
import kotlinx.coroutines.launch
import org.json.JSONArray

class QuranViewModel(private val repository: QuranRepository) : ViewModel() {

    private val _quranData = MutableLiveData<JSONArray>()
    val quranData: LiveData<JSONArray>
        get() = _quranData

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val data = repository.fetchQuranData()
            _quranData.postValue(data)
        }
    }
}