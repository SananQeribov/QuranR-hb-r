package com.legalist.quranrhbr.adapter


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

        val godName = godNameResponseData.data[position]
        holder.binding.textItemNumber.text = (position + 1).toString()
        holder.binding.textArabicName.text = godName.arabicName
        holder.binding.textTransliteration.text = godName.transliteration
        holder.binding.textEnglishTranslation.text = godName.englishTranslation
    }
}
