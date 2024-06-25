package com.legalist.quranrhbr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.databinding.ItemSurahBinding
import org.json.JSONArray
import org.json.JSONObject
import com.legalist.quranrhbr.manager.AudioManager
import com.legalist.quranrhbr.manager.ClipboardManager
import com.legalist.quranrhbr.manager.ShareManager

class SurahPagerAdapter(
    private val context: Context,
    private val audioManager: AudioManager,
    private val clipboardManager: ClipboardManager,
    private val shareManager: ShareManager
) : RecyclerView.Adapter<SurahPagerAdapter.SurahViewHolder>() {

    private val surahs = mutableListOf<JSONObject>()
    private var ayahPositions = mutableMapOf<Int, Int>()

    inner class SurahViewHolder(val binding: ItemSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ayahAdapter = AyahAdapter(context, audioManager, clipboardManager, shareManager)

        init {
            binding.recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            binding.recyclerView.adapter = ayahAdapter

            binding.btnCopy.setOnClickListener {
                val surah = surahs[adapterPosition]
                val surahContent =
                    clipboardManager.buildSurahContent(surah, ayahAdapter, adapterPosition)
                clipboardManager.copyToClipboard(surahContent)
            }

            binding.btnShare.setOnClickListener {
                val surah = surahs[adapterPosition]
                val surahContent =
                    clipboardManager.buildSurahContent(surah, ayahAdapter, adapterPosition)
                shareManager.shareText(itemView.context, surahContent)
            }

            binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    ayahPositions[adapterPosition] = layoutManager.findFirstVisibleItemPosition()
                }
            })
        }

        fun setCurrentAyahPosition(ayahPosition: Int) {
            binding.recyclerView.scrollToPosition(ayahPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahs[position]
        holder.binding.surahNumberTextView.text = " ${position + 1}"
        holder.binding.surahNameTextView.text = surah.getString("name")
//        val details = "English Name: ${surah.getString("englishName")}\n" +
//                "Translation: ${surah.getString("englishNameTranslation")}\n" +
//                "Revelation Type: ${surah.getString("revelationType")}"
//        holder.binding.surahDetailsTextView.text = details
        holder.binding.surahEnglishName.text = "${surah.getString("englishName")}"
        holder.binding.surahTranslation.text = "${surah.getString("englishNameTranslation")}"
        holder.binding.surahRevelation.text = "${surah.getString("revelationType")}"
        val ayahs = surah.getJSONArray("ayahs")
        holder.binding.ayahCountTextView.text = "${ayahs.length()} verses"
        holder.ayahAdapter.setAyahs(ayahs)

        // Restore the last saved scroll position
        val lastPosition = ayahPositions[position] ?: 0
        holder.binding.recyclerView.scrollToPosition(lastPosition)
    }

    override fun getItemCount(): Int = surahs.size

    fun setSurahs(surahs: JSONArray) {
        this.surahs.clear()
        for (i in 0 until surahs.length()) {
            this.surahs.add(surahs.getJSONObject(i))
        }
        notifyDataSetChanged()
    }

    fun getCurrentAyahPosition(surahPosition: Int): Int {
        return ayahPositions[surahPosition] ?: -1
    }
}