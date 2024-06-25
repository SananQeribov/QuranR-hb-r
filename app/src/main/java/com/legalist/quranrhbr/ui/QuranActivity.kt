package com.legalist.quranrhbr.ui
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ders.domain.util.ProgressBarCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.legalist.mylibrary.managers.repository.QuranRepository
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.AyahAdapter
import com.legalist.quranrhbr.adapter.SurahPagerAdapter
import com.legalist.quranrhbr.manager.AudioManager
import com.legalist.quranrhbr.manager.ClipboardManager
import com.legalist.quranrhbr.manager.ShareManager
import com.legalist.quranrhbr.viewModel.QuranViewModel
import com.legalist.quranrhbr.viewModelFactory.QuranViewModelFactory

class QuranActivity : AppCompatActivity(), ProgressBarCallback {


    private lateinit var viewModel: QuranViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var surahPagerAdapter: SurahPagerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var toolbar: Toolbar
    private lateinit var actionLeftButton: ImageButton
    private lateinit var actionHomeButton: ImageButton
    private lateinit var actionRightButton: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_quran)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewPager)
        progressBar = findViewById(R.id.progressBar)
        actionLeftButton = findViewById(R.id.action_left)
        actionHomeButton = findViewById(R.id.action_home)
        actionRightButton = findViewById(R.id.action_right)



        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Initialize the managers
        val audioManager = AudioManager(this)

        val clipboardManager = ClipboardManager(this)
        val shareManager = ShareManager()

        // Pass the managers to the adapter
        surahPagerAdapter = SurahPagerAdapter(this, audioManager, clipboardManager, shareManager)
        viewPager.adapter = surahPagerAdapter

        val repository = QuranRepository(this, this)
        viewModel = ViewModelProvider(
            this,
            QuranViewModelFactory(repository)
        ).get(QuranViewModel::class.java)

        observeData()


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewPager)

        actionLeftButton.setOnClickListener {
            navigateLeft()
        }

        actionHomeButton.setOnClickListener {
            onBackPressed()
        }

        actionRightButton.setOnClickListener {
            navigateRight()
        }




    }

    private fun observeData() {
        viewModel.quranData.observe(this, { surahs ->
            surahs?.let {
                surahPagerAdapter.setSurahs(it)
                val position = intent.getIntExtra("surah_number", -1)
                viewPager.post {
                    viewPager.setCurrentItem(position, false)

                }
                toolBarBackPress(position)

            }
        })

    }

    fun toolBarBackPress(position: Int) {
        toolbar.setNavigationOnClickListener {
            val observerPosition = position
            val currentItem = viewPager.currentItem
            if (currentItem > observerPosition) {
                viewPager.currentItem = currentItem - 1
            } else if (currentItem >= 0 && currentItem < observerPosition) {
                viewPager.currentItem = currentItem + 1
            } else {
                onBackPressed()
            }
        }
    }


    fun changePosition(surahposition: Int, ayahposition: Int) {
        viewPager.setCurrentItem(surahposition, false)
        viewPager.post {
            val currentSurahPosition = viewPager.currentItem
            val currentViewHolder =
                (viewPager.getChildAt(0) as? RecyclerView)?.findViewHolderForAdapterPosition(
                    currentSurahPosition
                ) as? SurahPagerAdapter.SurahViewHolder
            currentViewHolder?.setCurrentAyahPosition(ayahposition)
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
    private fun navigateLeft() {
        val currentPosition = viewPager.currentItem
        if (currentPosition > 0) {
            viewPager.setCurrentItem(currentPosition - 1, true)
        }
    }



    private fun navigateRight() {
        val currentPosition = viewPager.currentItem
        val totalItems = viewPager.adapter?.itemCount ?: 0
        if (currentPosition < totalItems - 1) {
            viewPager.setCurrentItem(currentPosition + 1, true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveButton -> {
                val currentSurahPosition = viewPager.currentItem
                val currentAyahPosition =
                    surahPagerAdapter.getCurrentAyahPosition(currentSurahPosition)

                sharedPreferences.edit().clear().apply()

                sharedPreferences.edit().putInt("surahPosition", currentSurahPosition).apply()
                sharedPreferences.edit().putInt("ayahPosition", currentAyahPosition).apply()

                Snackbar.make(viewPager, "Positions saved", Snackbar.LENGTH_SHORT).show()
                true
            }

            R.id.loadButton -> {
                val surahPosition = sharedPreferences.getInt("surahPosition", -1)
                val ayahPosition = sharedPreferences.getInt("ayahPosition", -1)

                if (surahPosition != -1 && ayahPosition != -1) {
                    changePosition(surahPosition, ayahPosition)
                } else {
                    Snackbar.make(viewPager, "Saved positions not found.", Snackbar.LENGTH_SHORT)
                        .show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}