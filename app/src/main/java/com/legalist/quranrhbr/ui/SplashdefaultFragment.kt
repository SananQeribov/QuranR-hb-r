package com.legalist.quranrhbr.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.legalist.quranrhbr.R
import androidx.navigation.fragment.findNavController




class SplashdefaultFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        // 5 saniyə = 5,000 millisaniyə
        val delayInMillis: Long = 5_000

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            findNavController().navigate(R.id.action_splashdefaultFragment_to_splashFragment)
        }, delayInMillis)





        return inflater.inflate(R.layout.fragment_splashdefault, container, false)
    }


}