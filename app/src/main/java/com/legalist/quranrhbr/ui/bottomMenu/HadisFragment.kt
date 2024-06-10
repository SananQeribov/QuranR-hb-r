package com.legalist.quranrhbr.ui.bottomMenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.legalist.quranrhbr.Application.MyApplication
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.FragmentHadisBinding
import com.legalist.quranrhbr.ui.InfoDialogFragment

class HadisFragment : Fragment() {
    private lateinit var binding: FragmentHadisBinding
    private var wakeLock: PowerManager.WakeLock? = null

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

        binding.toggleThemeButton.setOnClickListener {
            val myApp = activity?.application as? MyApplication
            myApp?.toggleDarkMode()
            activity?.recreate()
        }

        binding.exitButton.setOnClickListener {
            activity?.finishAffinity()
        }

        binding.privacyPolicy.setOnClickListener {
            showDialog("Privacy Policy", "Privacy Policy\n" +
                    "Last updated: January 11, 2022\n" +
                    "This Privacy Policy describes Our policies and procedures on the collection, use and disclosure of Your information when You use the Service and tells You about Your privacy rights and how the law protects You.\n" +
                    "We use Your Personal data to provide and improve the Service. By using the Service, You agree to the collection and use of information in accordance with this Privacy Policy. This Privacy Policy has been created with the help of the Privacy Policy Template.\n" +
                    "Interpretation and Definitions\n" +
                    "Interpretation\n" +
                    "The words of which the initial letter is capitalized have meanings defined under the following conditions. The following definitions shall have the same meaning regardless of whether they appear in singular or in plural.\n" +
                    "Definitions\n" +
                    "For the purposes of this Privacy Policy:\n" +
                    "Account means a unique account created for You to access our Service or parts of our Service.\n" +
                    "Affiliate means an entity that controls, is controlled by or is under common control with a party, where \"control\" means ownership of 50% or more of the shares, equity interest or other securities entitled to vote for election of directors or other managing authority.\n" +
                    "Application means the software program provided by the Company downloaded by You on any electronic device, named Quran App\n" +
                    "Company (referred to as either \"the Company\", \"We\", \"Us\" or \"Our\" in this Agreement) refers to Quran App.\n" +
                    "Country refers to: Tamil Nadu, India")
        }

        val screenOnSwitch = binding.root.findViewById<Switch>(R.id.screenOnSwitch)

        // Switch butonunun durumuna göre işlemleri yapma
        screenOnSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Ekranı daima açık tut
                acquireWakeLock()
            } else {
                // Ekranı kapat
                releaseWakeLock()
            }
        }

        binding.helpSupport.setOnClickListener {
            showDialog("Help & Support", "For further information about this app\n" +
                    "\n" +
                    "CONTACT: ibrahimrasith@gmail.com")
        }

        binding.about.setOnClickListener {
            showDialog("About", "Welcome to Quran App, your number one source for all good things. We're dedicated to providing you \n" +
                    "the best of agirah, with a focus on agirah.\n" +
                    "\n" +
                    "\n" +
                    "We're working to turn our mission towards agirah. We hope you get the \n" +
                    "good information as much as we gather offering them to you.\n" +
                    "\n" +
                    "\n" +
                    "Sincerely,\n" +
                    "\n" +
                    "Mohamed Ibrahim J")
        }

    }
    private fun acquireWakeLock() {
        val powerManager = requireActivity().getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "YourApp::YourWakeLockTag")
        wakeLock?.acquire()
    }

    private fun releaseWakeLock() {
        wakeLock?.release()
    }

    private fun showDialog(title: String, message: String) {
        val dialog = InfoDialogFragment(title, message)
        dialog.show(parentFragmentManager, "infoDialog")
    }
}
