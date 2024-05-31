package com.legalist.quranrhbr.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.legalist.mylibrary.managers.repository.QuranRepository
import com.legalist.quranrhbr.viewModel.QuranViewModel

class QuranViewModelFactory(private val repository: QuranRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuranViewModel::class.java)) {
            return QuranViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}