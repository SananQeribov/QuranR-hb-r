package com.legalist.quranrhbr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.legalist.mylibrary.managers.local.entity.Zikr
import com.legalist.quranrhbr.databinding.ListZikirBinding

class ZikirAdepter(private val zikirlist: List<Zikr>) :
    RecyclerView.Adapter<ZikirAdepter.HomeHolder>() {
    class HomeHolder(private val binding: ListZikirBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homes: Zikr) {
            binding.arabic.text = homes.arabicName
            binding.reading.text = homes.transliteration
            binding.queded.text = homes.id.toString()
            binding.transition.text = homes.englishTranslation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val biding = ListZikirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(biding)
    }


    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(zikirlist[position])
    }

    override fun getItemCount() = zikirlist.size
}


