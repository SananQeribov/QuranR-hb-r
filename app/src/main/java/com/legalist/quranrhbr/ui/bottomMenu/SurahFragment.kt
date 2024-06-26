package com.legalist.quranrhbr.ui.bottomMenu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.SurahAdapter
import com.legalist.quranrhbr.ui.QuranActivity
import com.legalist.quranrhbr.viewModel.SurahViewModel
import kotlinx.coroutines.launch

class SurahFragment : Fragment() {

    private val viewModel: SurahViewModel by viewModels()
    private lateinit var adapter: SurahAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_surah, container, false)
        progressBar = view.findViewById(R.id.progress_bar)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager

        val searchView: SearchView = view.findViewById(R.id.search_view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.surahs.collect { surahs ->
                if (surahs != null) {
                    adapter = SurahAdapter(surahs) { surahNumber ->
                        val intent = Intent(requireContext(), QuranActivity::class.java).apply {
                            putExtra("surah_number", surahNumber-1)
                        }
                        startActivity(intent)
                    }
                    recyclerView.adapter = adapter
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }

        val fabBottom: FloatingActionButton = view.findViewById(R.id.fab_bottom)
        fabBottom.setOnClickListener {
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            val itemCount = layoutManager.itemCount

            if (lastVisibleItemPosition != itemCount - 1) {
                recyclerView.smoothScrollToPosition(itemCount - 1)
                fabBottom.setImageResource(R.drawable.ic_arrow_up)
            } else {
                recyclerView.smoothScrollToPosition(0)
                fabBottom.setImageResource(R.drawable.ic_arrow_down)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}
