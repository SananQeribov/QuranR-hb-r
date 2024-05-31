package com.legalist.quranrhbr.viewModel




import androidx.lifecycle.ViewModel
import com.legalist.quranrhbr.R

class OnboardingViewModel : ViewModel() {

    data class OnboardingPage(
        val mainText: String,
        val subText: String,
        val imageResId: Int

    )

    val pages = listOf(
        OnboardingPage("Main Text 1", "Sub Text 1", R.drawable.zakat),
        OnboardingPage("Main Text 2", "Sub Text 2", R.drawable.zikir),
        OnboardingPage("Main Text 3", "Sub Text 3", R.drawable.namaz_eig)
    )
}
