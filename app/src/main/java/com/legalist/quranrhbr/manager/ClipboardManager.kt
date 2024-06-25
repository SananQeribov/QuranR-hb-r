package com.legalist.quranrhbr.manager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.legalist.quranrhbr.adapter.AyahAdapter
import org.json.JSONObject

class ClipboardManager(private val context: Context) {

    fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Ayah Text", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun buildSurahContent(surah: JSONObject, ayahAdapter: AyahAdapter, position: Int): String {
        val builder = StringBuilder()
        builder.append("Surah Number: ${position + 1}\n")
        builder.append("Name: ${surah.optString("name", "[No name]")}\n")
        builder.append("English Name: ${surah.optString("englishName", "[No English name]")}\n")
        builder.append("Translation: ${surah.optString("englishNameTranslation", "[No translation]")}\n")
        builder.append("Revelation Type: ${surah.optString("revelationType", "[No revelation type]")}\n")
        builder.append("Ayah Count: ${ayahAdapter.itemCount}\n")

        // Retrieve and append ayah texts from AyahAdapter
        for (ayahIndex in 0 until ayahAdapter.itemCount) {
            val ayah = ayahAdapter.getAyahAt(ayahIndex)
            builder.append("${ayahIndex + 1}. ${ayah.optString("textArabic", "[No text]")}\n")
            builder.append("${ayah.optString("textEnglish", "[No translation]")}\n")
        }

        return builder.toString()
    }
}
