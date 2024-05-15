package com.legalist.common.widgets.textviews

import android.content.Context
import android.graphics.Color

import android.graphics.Typeface

import android.text.style.StyleSpan
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatTextView



class FontTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setupAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setupAttrs(context, attrs)
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet?) {
        // Metin rengi ve büyüklüğü ayarları
        setTextColor(Color.parseColor("#300759"))
        textSize = 16f

        // Özelleştirilmiş metin oluşturma
        val text = "Quran App "

        val boldSpan = StyleSpan(Typeface.BOLD)

        setText(text)
    }
}