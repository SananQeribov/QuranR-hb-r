package com.legalist.quranrhbr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ders.domain.model.Data
import com.ders.domain.model.ZikirResponse
import com.legalist.quranrhbr.databinding.ListZikirBinding

class ZikirAdepter(private val zikirlist: ZikirResponse) : RecyclerView.Adapter<ZikirAdepter.HomeHolder>() {
    class HomeHolder(private val binding: ListZikirBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(homes: Data) {
            binding.arabic.text = homes.arabicName
            binding.reading.text = homes.transliteration
            binding.queded.text = homes.id.toString()
            binding.transition .text  = homes.englishTranslation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val biding = ListZikirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(biding)
    }


    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(zikirlist.data[position])
    }

    override fun getItemCount() = zikirlist.data.size
}


