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
        OnboardingPage("Zakat", "Contribute to the community by giving Zakat, a pillar of Islam that purifies your wealth and helps those in need.", R.drawable.zakat),
        OnboardingPage("Quran", "Illuminate your heart with the divine light and timeless wisdom of the Holy Quran", R.drawable.read),
        OnboardingPage("Prayer", "Prayer is your sacred connection to Allah. Embrace spiritual tranquility and inner peace with every prostration.", R.drawable.namaz_eig)
    )
}
