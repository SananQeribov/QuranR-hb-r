package com.legalist.quranrhbr.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R
import com.legalist.quranrhbr.databinding.ItemAyahBinding
import com.legalist.quranrhbr.manager.AudioManager
import com.legalist.quranrhbr.manager.ClipboardManager
import com.legalist.quranrhbr.manager.ShareManager
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class AyahAdapter(
    private val context: Context,
    private val audioManager: AudioManager,
    private val clipboardManager: ClipboardManager,
    private val shareManager: ShareManager
) : RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    private val ayahs = mutableListOf<JSONObject>()

    inner class AyahViewHolder(private val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val ayah = ayahs[position]

            binding.ayahNumberTextView.text = (position + 1).toString()
            binding.ayahArabicTextView.text = ayah.optString("textArabic")
            binding.ayahEnglishTextView.text = ayah.optString("textEnglish")

            binding.playButton.setOnClickListener {
                val audioUrl = ayahs[adapterPosition].optString("audio", null)
                audioUrl?.let {
                    val audioFile = File(itemView.context.filesDir, it.substringAfterLast("/"))
                    if (audioFile.exists()) {
                        audioManager.playAudio(audioFile.absolutePath)
                    } else {
                        audioManager.downloadAudio(it) { filePath ->
                            audioManager.playAudio(filePath)
                        }
                    }
                }
            }

            binding.pauseButton.setOnClickListener {
                audioManager.pauseAudio()
            }

            binding.stopButton.setOnClickListener {
                audioManager.stopAudio()
            }

            binding.copyButton.setOnClickListener {
                val ayah = ayahs[adapterPosition]
                val textToCopy = "${binding.ayahNumberTextView.text}\n${binding.ayahArabicTextView.text}\n${binding.ayahEnglishTextView.text}"
                clipboardManager.copyToClipboard(textToCopy)
            }

            binding.shareButton.setOnClickListener {
                val ayah = ayahs[adapterPosition]
                val shareMessage = "${binding.ayahNumberTextView.text}\n${binding.ayahArabicTextView.text}\n${binding.ayahEnglishTextView.text}"
                shareManager.shareText(itemView.context, shareMessage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val binding = ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AyahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        holder.bind(position)
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
}

