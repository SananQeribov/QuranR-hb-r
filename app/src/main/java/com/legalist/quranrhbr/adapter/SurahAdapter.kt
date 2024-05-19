package com.legalist.quranrhbr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ders.domain.model.Surah
import com.legalist.quranrhbr.R

// common/SurahAdapter.kt

import android.widget.Filter
import android.widget.Filterable

import java.util.*
import kotlin.collections.ArrayList

class SurahAdapter(private val surahList: List<Surah>) :
    RecyclerView.Adapter<SurahAdapter.SurahViewHolder>(), Filterable {

    private var filteredSurahList: List<Surah> = surahList

    class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.surah_name)
        val englishName: TextView = view.findViewById(R.id.surah_english_name)
        val translation: TextView = view.findViewById(R.id.surah_translation)
        val ayahsNumber: TextView = view.findViewById(R.id.ayahs_number)
        val revelationType:TextView= view.findViewById(R.id.revelationType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_item, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = filteredSurahList[position]
        holder.name.text = surah.name
        holder.englishName.text = surah.englishName
        holder.translation.text = surah.englishNameTranslation
        holder.ayahsNumber.text=surah.numberOfAyahs.toString()
        holder.revelationType.text = surah.revelationType
    }

    override fun getItemCount() = filteredSurahList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = mutableListOf<Surah>()

                if (constraint.isNullOrBlank()) {
                    filteredResults.addAll(surahList)
                } else {
                    val filterPattern = constraint.toString().trim().toLowerCase(Locale.getDefault())
                    for (item in surahList) {
                        if (item.name.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                            filteredResults.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredResults
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredSurahList = results?.values as List<Surah>
                notifyDataSetChanged()
            }
        }
    }
}

