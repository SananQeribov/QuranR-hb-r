

package com.legalist.quranrhbr.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ders.domain.util.ProgressBarCallback
import com.google.android.material.snackbar.Snackbar
import com.legalist.mylibrary.managers.repository.QuranRepository
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.SurahPagerAdapter
import com.legalist.quranrhbr.viewModel.QuranViewModel
import com.legalist.quranrhbr.viewModelFactory.QuranViewModelFactory
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QuranActivity : AppCompatActivity(), ProgressBarCallback {


    private lateinit var viewModel: QuranViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var surahPagerAdapter: SurahPagerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_quran)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        viewPager = findViewById(R.id.viewPager)
        progressBar = findViewById(R.id.progressBar)
        surahPagerAdapter = SurahPagerAdapter()
        viewPager.adapter = surahPagerAdapter

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)


        val repository = QuranRepository(this, this)
        viewModel = ViewModelProvider(this, QuranViewModelFactory(repository)).get(QuranViewModel::class.java)

        observeData()
    }

    private fun observeData() {
        viewModel.quranData.observe(this, { surahs ->
            surahs?.let {
                surahPagerAdapter.setSurahs(it)
                val position = intent.getIntExtra("position", -1)
                viewPager.post {
                    viewPager.setCurrentItem(position, false)
                }
            }
        })
    }
    fun changePosition(surahposition: Int, ayahposition: Int) {
        viewPager.setCurrentItem(surahposition, false)
       // Timber.d("surah position used")
        viewPager.post {
            val currentSurahPosition = viewPager.currentItem
            val currentViewHolder = (viewPager.getChildAt(0) as? RecyclerView)?.findViewHolderForAdapterPosition(currentSurahPosition) as? SurahPagerAdapter.SurahViewHolder
            currentViewHolder?.setCurrentAyahPosition(ayahposition)
           // Timber.d("current position used")
        }
    }


    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.quran_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveButton -> {
                val currentSurahPosition = viewPager.currentItem
                val currentAyahPosition = surahPagerAdapter.getCurrentAyahPosition(currentSurahPosition)
//                val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

                val ayahPosition = currentAyahPosition
                val surahPosition = currentSurahPosition

                sharedPreferences.edit().clear().apply()

                // Save current positions with unique keys
                sharedPreferences.edit().putInt("surahPosition", surahPosition).apply()
                sharedPreferences.edit().putInt("ayahPosition", ayahPosition).apply()

                Snackbar.make(viewPager, "Positions saved", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.loadButton -> {
                // Load all saved positions
                val surahPositionKey = "surahPosition"
                val ayahPositionKey = "ayahPosition"
                val surahPosition = loadSavedPosition(surahPositionKey)
                val ayahPosition = loadSavedPosition(ayahPositionKey)

                if (surahPosition != null && ayahPosition != null) {

                    changePosition(surahPosition,ayahPosition)

                } else {
                    println("Saved positions not found.")
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadSavedPosition(key: String): Int? {
        return sharedPreferences.getInt(key, -1)
    }


}



