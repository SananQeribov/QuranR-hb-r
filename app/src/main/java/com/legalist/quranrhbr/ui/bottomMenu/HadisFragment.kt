package com.legalist.quranrhbr.ui.bottomMenu

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.legalist.quranrhbr.Application.MyApplication
import com.legalist.quranrhbr.databinding.FragmentHadisBinding

class HadisFragment : Fragment() {
    private lateinit var binding: FragmentHadisBinding
    private var isAutoRotateEnabled = true // Başlangıçta otomatik döndürme açık

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHadisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tema değiştirme butonuna tıklama olayı
        binding.toggleThemeButton.setOnClickListener {
            val myApp = activity?.application as? MyApplication
            myApp?.toggleDarkMode()
            activity?.recreate() // Değişikliklerin uygulanması için aktiviteyi yeniden oluşturur
        }


        // Çıkış butonuna tıklama olayı
        binding.exitButton.setOnClickListener {
            activity?.finishAffinity() // Uygulamayı kapatır
        }
    }


}











