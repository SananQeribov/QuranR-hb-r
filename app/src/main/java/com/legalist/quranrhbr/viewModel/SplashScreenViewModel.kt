package com.legalist.quranrhbr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.legalist.quranrhbr.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenViewModel : ViewModel() {


    private var isSplashScreenShown = false

    fun navigateToNextFragment(navController: NavController) {
        if (!isSplashScreenShown) {
            isSplashScreenShown = true
            viewModelScope.launch {
                delay(2000)
                navController.navigate(R.id.action_splashFragment_to_onboardingFragment)
            }
        }
    }
}