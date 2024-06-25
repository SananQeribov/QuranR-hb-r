package com.legalist.quranrhbr.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.legalist.quranrhbr.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//class SplashdefaultFragment : Fragment() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//
//        // 5 saniyə = 5,000 millisaniyə
//        val delayInMillis: Long = 5_000
//
//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed({
//            findNavController().navigate(R.id.action_splashdefaultFragment_to_splashFragment)
//        }, delayInMillis)
//
//
//
//
//
//
//
//        return inflater.inflate(R.layout.fragment_splashdefault, container, false)
//    }
//
//
//}
class SplashdefaultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splashdefault, container, false)
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)

            withContext(Dispatchers.Main) {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    findNavController().navigate(R.id.action_splashdefaultFragment_to_splashFragment)
                }
            }
        }
    }
}
