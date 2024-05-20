package com.legalist.quranrhbr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import com.legalist.quranrhbr.databinding.ActivityMainBinding
import com.legalist.quranrhbr.databinding.FragmentPresentationBinding


class HomeFragment : Fragment() {
private lateinit var  binding:FragmentPresentationBinding
private  lateinit var  main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentationBinding.bind(view)
        main.bottomNav.isVisible = isVisible


    }
}