package com.legalist.quranrhbr.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ders.domain.model.Surah
import com.legalist.quranrhbr.R
import java.util.Locale


class SurahAdapter(
    private val surahList: List<Surah>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<SurahAdapter.SurahViewHolder>(), Filterable {

    private var filteredSurahList: List<Surah> = surahList
    private var lastPosition = -1

    inner class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.findViewById(R.id.surah_number)
        val name: TextView = view.findViewById(R.id.surah_name)
        val englishName: TextView = view.findViewById(R.id.surah_english_name)
        val translation: TextView = view.findViewById(R.id.surah_translation)
        val ayahsNumber: TextView = view.findViewById(R.id.ayahs_number)
        val revelationType: TextView = view.findViewById(R.id.revelationType)
        val cardview: CardView = view.findViewById(R.id.card)

        init {
            view.setOnClickListener {
                onItemClick.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_item, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SurahViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val surah = filteredSurahList[position]
        holder.number.text = (position + 1).toString()
        holder.name.text = surah.name
        holder.englishName.text = surah.englishName
        holder.translation.text = "Translation: ${surah.englishNameTranslation}"
        holder.ayahsNumber.text = "Number of Ayahs: ${surah.numberOfAyahs.toString()}"
        holder.revelationType.text = "Revelaton: ${surah.revelationType}"
        if (position > lastPosition) {
            //TranslateAnimation anim = new TranslateAnimation(0,-1000,0,-1000);
            val anim = ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            //anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            anim.duration = 550 //to make duration random number between [0,501)
            holder.cardview.startAnimation(anim)
            lastPosition = position
        }
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount() = filteredSurahList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = mutableListOf<Surah>()

                if (constraint.isNullOrBlank()) {
                    filteredResults.addAll(surahList)
                } else {
                    val filterPattern =
                        constraint.toString().trim().toLowerCase(Locale.getDefault())
                    for (item in surahList) {
                        if (item.name.toLowerCase(Locale.getDefault()).contains(filterPattern) ||
                            item.englishName.toLowerCase(Locale.getDefault())
                                .contains(filterPattern) ||
                            item.englishNameTranslation.toLowerCase(Locale.getDefault())
                                .contains(filterPattern)
                        ) {
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
