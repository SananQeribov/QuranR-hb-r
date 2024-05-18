package com.legalist.quranrhbr

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.legalist.quranrhbr.adapter.Adapter
import com.legalist.quranrhbr.databinding.ActivityMainBinding
import com.legalist.quranrhbr.ui.HomeFragment
import com.legalist.quranrhbr.ui.PrayertimeFragment
import com.legalist.quranrhbr.ui.QuranFragment

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.legalist.quranrhbr.ui.LoginFragment
import com.legalist.quranrhbr.ui.SplashdefaultFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var myViewPager2: ViewPager2? = null
    var myAdapter: Adapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
//        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.setOnItemSelectedListener { menuitem ->
            when (menuitem.itemId) {

                R.id.home -> {
                    binding.bottomNav.isVisible = true
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.quran -> {
                    binding.bottomNav.isVisible = true
                    replaceFragment(QuranFragment())

                    true
                }

                R.id.mosque -> {
                    binding.bottomNav.isVisible = true
                    replaceFragment(PrayertimeFragment())

        setContentView(R.layout.activity_main)
    

    }
}
