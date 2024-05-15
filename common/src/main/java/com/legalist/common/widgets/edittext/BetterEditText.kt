package com.legalist.common.widgets.edittext

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import com.legalist.common.R




class BetterEditText : AppCompatEditText, TextView.OnEditorActionListener {

    private val gradientDrawable = GradientDrawable()
    private var cornerRadius: Float = 0f

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setupAttrs(context, attrs)
        setOnEditorActionListener(this)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setupAttrs(context, attrs)
        setOnEditorActionListener(this)
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet?) {
        val headlineTypeface: Typeface = Typeface.create("Urbanist", Typeface.BOLD)
        typeface = headlineTypeface
        val betterEditText = BetterEditText(context)
        betterEditText.setCustomHeight(56)
    }

    // Yüksekliği ayarlamak için bir metod
    fun setCustomHeight(height: Int) {
        val layoutParams = layoutParams
        layoutParams.height = height
        setLayoutParams(layoutParams)
    }


    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        TODO("Not yet implemented")
    }
}
