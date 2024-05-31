package com.legalist.quranrhbr.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R
import org.json.JSONArray
import org.json.JSONObject

class SurahPagerAdapter : RecyclerView.Adapter<SurahPagerAdapter.SurahViewHolder>() {

    private val surahs = mutableListOf<JSONObject>()

    inner class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val surahNameTextView: TextView = itemView.findViewById(R.id.surahNameTextView)
        val surahDetailsTextView: TextView = itemView.findViewById(R.id.surahDetailsTextView)
        val ayahRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
        val ayahAdapter = AyahAdapter()

        init {
            ayahRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            ayahRecyclerView.adapter = ayahAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahs[position]
        holder.surahNameTextView.text = surah.getString("name")
        val details = "English Name: ${surah.getString("englishName")}\n" +
                "Translation: ${surah.getString("englishNameTranslation")}\n" +
                "Revelation Type: ${surah.getString("revelationType")}"
        holder.surahDetailsTextView.text = details
        holder.ayahAdapter.setAyahs(surah.getJSONArray("ayahs"))
    }

    override fun getItemCount(): Int = surahs.size

    fun setSurahs(surahs: JSONArray) {
        this.surahs.clear()
        for (i in 0 until surahs.length()) {
            this.surahs.add(surahs.getJSONObject(i))
        }
        notifyDataSetChanged()
    }


}