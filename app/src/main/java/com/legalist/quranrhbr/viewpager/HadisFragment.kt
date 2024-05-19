package com.legalist.quranrhbr.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.FragmentHadisBinding


class HadisFragment : Fragment() {
   private lateinit var binding:FragmentHadisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHadisBinding.inflate(inflater ,container, false)
        return  binding.root
    }


}