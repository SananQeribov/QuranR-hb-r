package com.legalist.quranrhbr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.legalist.quranrhbr.adapter.Adapter
import com.legalist.quranrhbr.databinding.ActivityMainBinding
import com.legalist.quranrhbr.ui.HomeFragment
import com.legalist.quranrhbr.ui.PrayertimeFragment
import com.legalist.quranrhbr.ui.QuranFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var myViewPager2: ViewPager2? = null
    var myAdapter: Adapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

                    true
                }
                else -> false
            }

        }
        // replaceFragment(MapsFragment())

    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

