package com.legalist.quranrhbr.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.legalist.quranrhbr.R
import com.otaliastudios.zoom.ZoomLayout
import org.json.JSONArray
import org.json.JSONObject

class SurahPagerAdapter : RecyclerView.Adapter<SurahPagerAdapter.SurahViewHolder>() {

    private val surahs = mutableListOf<JSONObject>()
    private var ayahPositions = mutableMapOf<Int, Int>()



    inner class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val surahNumberTextView: TextView = itemView.findViewById(R.id.surahNumberTextView)
        val surahNameTextView: TextView = itemView.findViewById(R.id.surahNameTextView)
        val surahDetailsTextView: TextView = itemView.findViewById(R.id.surahDetailsTextView)
        val ayahCountTextView: TextView = itemView.findViewById(R.id.ayahCountTextView)
        val ayahRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
        val ayahAdapter = AyahAdapter()
        val btnCopy: ImageButton = itemView.findViewById(R.id.btnCopy)
        val btnShare: ImageButton = itemView.findViewById(R.id.btnShare)





        init {
            ayahRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            ayahRecyclerView.adapter = ayahAdapter

            btnCopy.setOnClickListener {
                val surah = surahs[adapterPosition]
                val surahContent = buildSurahContent(surah)
                copyToClipboard(itemView.context, surahContent)
            }

            btnShare.setOnClickListener {
                val surah = surahs[adapterPosition]
                val surahContent = buildSurahContent(surah)
                shareContent(itemView.context, surahContent)
            }

            ayahRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    ayahPositions[adapterPosition] = layoutManager.findFirstVisibleItemPosition()
                }
            })



        }



        fun setCurrentAyahPosition(ayahPosition: Int) {
            ayahRecyclerView.scrollToPosition(ayahPosition)
        }


        private fun buildSurahContent(surah: JSONObject): String {
            val builder = StringBuilder()
            builder.append("Surah Number: ${adapterPosition + 1}\n")
            builder.append("Name: ${surah.optString("name", "[No name]")}\n")
            builder.append("English Name: ${surah.optString("englishName", "[No English name]")}\n")
            builder.append("Translation: ${surah.optString("englishNameTranslation", "[No translation]")}\n")
            builder.append("Revelation Type: ${surah.optString("revelationType", "[No revelation type]")}\n")
            builder.append("Ayah Count: ${ayahAdapter.itemCount}\n")

            // Retrieve and append ayah texts from AyahAdapter
            for (ayahIndex in 0 until ayahAdapter.itemCount) {
                val ayah = ayahAdapter.getAyahAt(ayahIndex)
                builder.append("${ayahIndex + 1}. ${ayah.optString("textArabic", "[No text]")}\n")
                builder.append("${ayah.optString("textEnglish", "[No translation]")}\n")
            }

            return builder.toString()
        }

        private fun copyToClipboard(context: Context, text: String) {
            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Surah Content", text)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        private fun shareContent(context: Context, text: String) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahs[position]
        holder.surahNumberTextView.text = "Surah Number: ${position + 1}"
        holder.surahNameTextView.text = surah.getString("name")
        val details = "English Name: ${surah.getString("englishName")}\n" +
                "Translation: ${surah.getString("englishNameTranslation")}\n" +
                "Revelation Type: ${surah.getString("revelationType")}"
        holder.surahDetailsTextView.text = details
        val ayahs = surah.getJSONArray("ayahs")
        holder.ayahCountTextView.text = "Ayah Count: ${ayahs.length()}"
        holder.ayahAdapter.setAyahs(ayahs)




        // Restore the last saved scroll position
        val lastPosition = ayahPositions[position] ?: 0
        holder.ayahRecyclerView.scrollToPosition(lastPosition)
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
    fun setCurrentAyahPosition(surahPosition: Int, ayahPosition: Int) {
        ayahPositions[surahPosition] = ayahPosition
    }













}
