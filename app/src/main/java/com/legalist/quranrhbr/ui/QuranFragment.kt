package com.legalist.quranrhbr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.Adapter
import com.legalist.quranrhbr.databinding.ActivityMainBinding
import com.legalist.quranrhbr.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {
  private lateinit var binding: FragmentQuranBinding
  private  lateinit var main :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuranBinding.inflate(inflater, container, false)
        main.bottomNav.isVisible = true


        return  binding.root
    }


}