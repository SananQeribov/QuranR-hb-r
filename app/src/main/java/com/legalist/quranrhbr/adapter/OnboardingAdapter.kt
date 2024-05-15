package com.legalist.quranrhbr.adapter

import com.legalist.quranrhbr.viewModel.OnboardingViewModel



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R

class OnboardingAdapter(private val pages: List<OnboardingViewModel.OnboardingPage>) : RecyclerView.Adapter<OnboardingAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_onboarding, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val page = pages[position]
        holder.mainTextView.text = page.mainText
        holder.subTextView.text = page.subText
        holder.imageView.setImageResource(page.imageResId)
    }

    override fun getItemCount(): Int = pages.size

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val mainTextView: TextView = itemView.findViewById(R.id.textView6)
        val subTextView: TextView = itemView.findViewById(R.id.textView10)
    }
}
