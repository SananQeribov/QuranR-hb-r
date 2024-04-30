package com.legalist.quranrhbr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var  binding:FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
     binding = FragmentSplashBinding.inflate(inflater, container, false)
        return  binding.root
    }

}