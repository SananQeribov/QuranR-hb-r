package com.legalist.quranrhbr.ui.bottomMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.ActivityMainBinding
import com.legalist.quranrhbr.databinding.FragmentPrayertimeBinding



class PrayertimeFragment : Fragment() {
  private lateinit var binding:FragmentPrayertimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPrayertimeBinding.inflate(inflater, container, false)

        return  binding.root
    }


}