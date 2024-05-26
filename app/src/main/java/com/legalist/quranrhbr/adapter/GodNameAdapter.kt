package com.legalist.quranrhbr.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ders.domain.model.GodNameModel
import com.ders.domain.model.GodNameResponseData

import com.legalist.quranrhbr.databinding.RowLayoutBinding


class GodNameAdapter(private val godNameResponseData: GodNameResponseData, private val listener: Listener) : RecyclerView.Adapter<GodNameAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(godNameModel: GodNameModel)
    }

    //private val colors: Array<String> = arrayOf("#7B15BF", "#66548D", "#7B15BF", "#66548D", "#7B15BF", "#66548D", "#7B15BF", "#66548D")


    class RowHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return godNameResponseData.data.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(godNameResponseData.data[position])
        }
       // holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        val godName = godNameResponseData.data[position]
        holder.binding.textArabicName.text = godName.arabicName
        holder.binding.textTransliteration.text = godName.transliteration
        holder.binding.textEnglishTranslation.text = godName.englishTranslation
    }
}
