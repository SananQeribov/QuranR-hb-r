package com.legalist.quranrhbr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.adapter.OnboardingAdapter
import com.legalist.quranrhbr.viewModel.OnboardingViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnboardingFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var nextButton: Button
    private lateinit var skipTextView: TextView

    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        viewPager = view.findViewById(R.id.viewPager2)
        dotsIndicator = view.findViewById(R.id.dots_indicator)
        nextButton = view.findViewById(R.id.buttonNext)
        skipTextView = view.findViewById(R.id.textViewSkip)

        val adapter = OnboardingAdapter(viewModel.pages)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    nextButton.text = "Get Started"
                    skipTextView.visibility = View.INVISIBLE
                    skipTextView.isClickable = false
                } else {
                    nextButton.text = "Next"
                    skipTextView.visibility = View.VISIBLE
                    skipTextView.isClickable = true
                }
            }
        })

        nextButton.setOnClickListener {
            if (viewPager.currentItem < adapter.itemCount - 1) {
                viewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
            }
        }

        skipTextView.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewPager.currentItem > 0) {
                    viewPager.currentItem -= 1
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        return view
    }
}
