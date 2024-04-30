package com.legalist.quranrhbr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.FragmentPresentationBinding
import com.legalist.quranrhbr.databinding.FragmentSplashBinding


class PresentationFragment : Fragment() {
private lateinit var  binding:FragmentPresentationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentationBinding.bind(view)


    }
}