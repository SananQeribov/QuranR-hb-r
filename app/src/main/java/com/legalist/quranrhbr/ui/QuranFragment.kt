//package com.legalist.quranrhbr.ui
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ProgressBar
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.viewpager2.widget.ViewPager2
//import com.ders.domain.util.ProgressBarCallback
//import com.legalist.mylibrary.managers.repository.QuranRepository
//import com.legalist.quranrhbr.R
//import com.legalist.quranrhbr.adapter.SurahPagerAdapter
//import com.legalist.quranrhbr.viewModel.QuranViewModel
//import com.legalist.quranrhbr.viewModelFactory.QuranViewModelFactory
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import org.json.JSONArray
//class QuranFragment : Fragment(), ProgressBarCallback {
//
//    private lateinit var viewModel: QuranViewModel
//    private lateinit var viewPager: ViewPager2
//    private lateinit var surahPagerAdapter: SurahPagerAdapter
//    private lateinit var progressBar: ProgressBar
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_quran, container, false)
//
//        viewPager = view.findViewById(R.id.viewPager)
//        progressBar = view.findViewById(R.id.progressBar)
//        surahPagerAdapter = SurahPagerAdapter()
//        viewPager.adapter = surahPagerAdapter
//
//        val repository = QuranRepository(requireContext(), this)
//        viewModel = ViewModelProvider(this, QuranViewModelFactory(repository)).get(QuranViewModel::class.java)
//
//        observeData()
//        val position = arguments?.getInt("position", 0)
//
//
//
//        // Belirtilen pozisyona geç
//        position?.let {
//            viewPager.post {
//                viewPager.setCurrentItem(it, false)
//            }
//        }
//
//        return view
//    }
//
//    private fun observeData() {
//        viewModel.quranData.observe(viewLifecycleOwner, { surahs ->
//            surahs?.let {
//                surahPagerAdapter.setSurahs(it)
//            }
//        })
//    }
//
//    override fun showProgressBar() {
//        progressBar.visibility = View.VISIBLE
//    }
//
//    override fun hideProgressBar() {
//        progressBar.visibility = View.GONE
//    }
//}