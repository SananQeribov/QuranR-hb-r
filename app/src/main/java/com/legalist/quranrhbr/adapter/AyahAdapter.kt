package com.legalist.quranrhbr.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

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
        val copyButton: ImageButton = itemView.findViewById(R.id.copyButton)
        val shareButton: ImageButton = itemView.findViewById(R.id.shareButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayah, parent, false)
        return AyahViewHolder(view)

    }
    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        val ayah = ayahs[position]

        holder.ayahNumberTextView.text = (position + 1).toString()
        holder.ayahArabicTextView.text = ayah.getString("textArabic")
        holder.ayahEnglishTextView.text = ayah.getString("textEnglish")

        val audioUrl = ayah.optString("audio", null)

        holder.playButton.setOnClickListener {
            audioUrl?.let {
                val audioFile = File(holder.itemView.context.filesDir, it.substringAfterLast("/"))
                if (audioFile.exists()) {
                    playAudio(audioFile.absolutePath)
                } else {
                    downloadAudio(it, holder.itemView.context) { filePath ->
                        playAudio(filePath)
                    }
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

        holder.copyButton.setOnClickListener {
            val clipboard = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val textToCopy = "${holder.ayahNumberTextView.text} \n ${holder.ayahArabicTextView.text}\n${holder.ayahEnglishTextView.text}"
            val clip = ClipData.newPlainText("Ayah Text", textToCopy)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(holder.itemView.context, "Ayah copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        holder.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "${holder.ayahNumberTextView.text} \n ${holder.ayahArabicTextView.text}\n${holder.ayahEnglishTextView.text}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share Ayah via"))
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

    fun getAyahAt(position: Int): JSONObject {
        return ayahs[position]
    }

    private fun playAudio(filePath: String) {
        if (currentAudioUrl == filePath && isMediaPlayerPrepared) {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            } else {
                mediaPlayer.pause()
            }
        } else {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            currentAudioUrl = filePath
            mediaPlayer.setDataSource(filePath)
            mediaPlayer.prepareAsync()
        }
    }





private fun downloadAudio(url: String, context: Context, callback: (filePath: String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.connect()
            val inputStream: InputStream = urlConnection.inputStream
            val file = File(context.filesDir, url.substringAfterLast("/"))
            val outputStream: OutputStream = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var len: Int
            while (inputStream.read(buffer).also { len = it } != -1) {
                outputStream.write(buffer, 0, len)
            }
            outputStream.close()
            inputStream.close()
            withContext(Dispatchers.Main) {
                callback(file.absolutePath)
            }
        } catch (e: IOException) {
            Log.e("AyahAdapter", "Error downloading audio", e)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Error downloading audio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
    fun scrollToPosition(recyclerView: RecyclerView, position: Int) {
        recyclerView.scrollToPosition(position)
    }
}
