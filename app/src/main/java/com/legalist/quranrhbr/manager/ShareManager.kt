package com.legalist.quranrhbr.manager


import android.content.Context
import android.content.Intent

class ShareManager {

    fun shareText(context: Context, text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(shareIntent, "Share Ayah via"))
    }
}
