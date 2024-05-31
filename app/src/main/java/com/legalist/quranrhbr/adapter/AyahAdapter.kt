package com.legalist.quranrhbr.adapter


import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class AyahAdapter : RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    private val ayahs = mutableListOf<JSONObject>()
    private val mediaPlayer = MediaPlayer()

    inner class AyahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ayahNumberTextView: TextView = itemView.findViewById(R.id.ayahNumberTextView)
        val ayahArabicTextView: TextView = itemView.findViewById(R.id.ayahArabicTextView)
        val ayahEnglishTextView: TextView = itemView.findViewById(R.id.ayahEnglishTextView)
        val playButton: ImageButton = itemView.findViewById(R.id.playButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayah, parent, false)
        return AyahViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        val ayah = ayahs[position]

        holder.ayahNumberTextView.text = (position + 1).toString()
        holder.ayahArabicTextView.text = ayah.getString("textArabic")
        holder.ayahEnglishTextView.text = ayah.getString("textEnglish")
        holder.playButton.setOnClickListener {
            val audioUrl = ayah.optString("audio", null)
            audioUrl?.let {
                try {
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(it)
                    mediaPlayer.setOnPreparedListener {
                        if (!mediaPlayer.isPlaying) {
                            mediaPlayer.start()
                        }
                    }
                    mediaPlayer.prepareAsync()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun getItemCount(): Int = ayahs.size

    fun setAyahs(ayahs: JSONArray) {
        this.ayahs.clear()
        for (i in 0 until ayahs.length()) {
            this.ayahs.add(ayahs.getJSONObject(i))
        }
        notifyDataSetChanged()
    }
}