package com.legalist.quranrhbr.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
private lateinit var binding:FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signoutFragment)
        }
        return  binding.root
    }


}