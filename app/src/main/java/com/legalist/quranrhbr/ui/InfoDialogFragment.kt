package com.legalist.quranrhbr.ui


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.legalist.quranrhbr.R

class InfoDialogFragment(private val title: String, private val message: String) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return inflater.inflate(R.layout.fragment_info_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.dialogTitle).text = title
        view.findViewById<TextView>(R.id.dialogMessage).text = message

        view.findViewById<Button>(R.id.closeButton).setOnClickListener {
            dismiss()
        }
    }
}
