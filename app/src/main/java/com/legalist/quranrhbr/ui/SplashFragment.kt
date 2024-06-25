package com.legalist.quranrhbr.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.FragmentSplashBinding
import com.legalist.quranrhbr.viewModel.SplashScreenViewModel

class SplashFragment : Fragment() {
    private lateinit var  binding:FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
     binding = FragmentSplashBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        val viewModel = ViewModelProvider(requireActivity())[SplashScreenViewModel::class.java]
        viewModel.navigateToNextFragment(findNavController())
    }
}