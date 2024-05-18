package com.legalist.quranrhbr.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.Adapter
import com.legalist.quranrhbr.databinding.FragmentNamazBinding
import com.legalist.quranrhbr.databinding.FragmentSurahBinding


class SurahFragment : Fragment() {
private  lateinit var  binding: FragmentSurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

      }

