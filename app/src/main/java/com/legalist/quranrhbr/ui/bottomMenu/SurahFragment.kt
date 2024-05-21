package com.legalist.quranrhbr.ui.bottomMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.SurahAdapter
import com.legalist.quranrhbr.viewModel.SurahViewModel

class SurahFragment : Fragment() {

    private val viewModel: SurahViewModel by viewModels()
    private lateinit var adapter: SurahAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_surah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)




        viewModel.surahs.observe(viewLifecycleOwner, Observer { surahs ->
            if (surahs != null) {
                adapter = SurahAdapter(surahs)
                recyclerView.adapter = adapter
                progressBar.visibility = View.GONE
            }else{
                progressBar.visibility = View.VISIBLE
            }
        })
    }
}