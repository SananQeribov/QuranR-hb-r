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
    private var currentAudioUrl: String? = null
    private var isMediaPlayerPrepared = false

    private val mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener {
            isMediaPlayerPrepared = true
            start()
        }
        setOnCompletionListener {
            isMediaPlayerPrepared = false
            stop()
            prepareAsync()
        }
    }

    inner class AyahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ayahNumberTextView: TextView = itemView.findViewById(R.id.ayahNumberTextView)
        val ayahArabicTextView: TextView = itemView.findViewById(R.id.ayahArabicTextView)
        val ayahEnglishTextView: TextView = itemView.findViewById(R.id.ayahEnglishTextView)
        val playButton: ImageButton = itemView.findViewById(R.id.playButton)
        val pauseButton: ImageButton = itemView.findViewById(R.id.pauseButton)
        val stopButton: ImageButton = itemView.findViewById(R.id.stopButton)
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

        val audioUrl = ayah.optString("audio", null)

        holder.playButton.setOnClickListener {
            audioUrl?.let {
                if (currentAudioUrl == it && isMediaPlayerPrepared) {
                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start()
                    } else {
                        mediaPlayer.pause()
                    }
                } else {
                    currentAudioUrl = it
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(it)
                    mediaPlayer.prepareAsync()
                }
            }
        }

        holder.pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        holder.stopButton.setOnClickListener {
            if (mediaPlayer.isPlaying || mediaPlayer.isLooping) {
                mediaPlayer.stop()
                isMediaPlayerPrepared = false
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

