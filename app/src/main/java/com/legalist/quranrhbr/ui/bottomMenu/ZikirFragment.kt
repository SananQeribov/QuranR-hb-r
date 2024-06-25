package com.legalist.quranrhbr.ui.bottomMenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.legalist.quranrhbr.adapter.ZikirAdepter
import com.legalist.quranrhbr.databinding.FragmentZikirBinding
import com.legalist.quranrhbr.viewModel.ZikirViewModel

class ZikirFragment : Fragment() {
    private lateinit var binding: FragmentZikirBinding
    private val viewModel: ZikirViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentZikirBinding.inflate(inflater, container, false)

        // ---- layout no attacked error
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycle.layoutManager = layoutManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
    }

    private fun load() {
        viewModel.refereshdata()

        viewModel.zikirs.observe(viewLifecycleOwner) { zikirs ->
            zikirs.let {
                val adapter = ZikirAdepter(it)
                Log.e("BBBBB", it.toString())
                binding.recycle.adapter = adapter
                binding.recycle.visibility = View.VISIBLE

            }
        }

        viewModel.zikirerror.observe(viewLifecycleOwner) {
            if (it) {
                //hata var
                binding.countryError.visibility = View.VISIBLE

            } else {
                //hata yok
                binding.countryError.visibility = View.GONE

            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                //yükleniyor
                binding.countryLoading.visibility = View.VISIBLE
                binding.recycle.visibility = View.GONE
                binding.countryError.visibility = View.GONE
            } else {
                binding.countryLoading.visibility = View.VISIBLE
            }
        }

    }

}

