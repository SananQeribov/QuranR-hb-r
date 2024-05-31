package com.legalist.quranrhbr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.legalist.mylibrary.managers.local.room.db.ZikrDatabase
import com.legalist.quranrhbr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Thread.sleep(3000)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ZikrDatabase(this)

//        installSplashScreen()


//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            binding.bottomNav.isVisible = destination.id == R.id.homeFragment
//        }
//        binding.bottomNav.setupWithNavController(navController)


//        binding.bottomNav.setOnItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.home -> {
//                    binding.bottomNav.isVisible = true
//                    replaceFragment(SettingFragment())
//                    true
//                }
//                R.id.quran -> {
//                    binding.bottomNav.isVisible = true
//                    replaceFragment(SurahFragment())
//                    true
//                }
//                R.id.mosque -> {
//                    binding.bottomNav.isVisible = true
//                    replaceFragment(PrayertimeFragment())
//                    true
//                }
//                else -> false
//            }
//        }


    }

//    private fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fragmentContainerView, fragment)
//            commit()
//        }
//    }
}
