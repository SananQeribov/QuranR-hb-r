package com.legalist.quranrhbr.ui

import android.view.View
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ders.domain.util.ProgressBarCallback
import com.legalist.mylibrary.managers.repository.QuranRepository
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.SurahPagerAdapter
import com.legalist.quranrhbr.viewModel.QuranViewModel
import com.legalist.quranrhbr.viewModelFactory.QuranViewModelFactory

class QuranActivity : AppCompatActivity(), ProgressBarCallback {

    private lateinit var viewModel: QuranViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var surahPagerAdapter: SurahPagerAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_quran)

        viewPager = findViewById(R.id.viewPager)
        progressBar = findViewById(R.id.progressBar)
        surahPagerAdapter = SurahPagerAdapter()
        viewPager.adapter = surahPagerAdapter

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

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
