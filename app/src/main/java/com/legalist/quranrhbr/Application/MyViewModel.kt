package com.legalist.quranrhbr.Application





import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


class MyViewModel : ViewModel() {
    val isDarkMode = MutableLiveData<Boolean>()

    init {

        isDarkMode.value = false
    }

    fun toggleDarkMode() {
        isDarkMode.value = !(isDarkMode.value ?: false)
    }
}




