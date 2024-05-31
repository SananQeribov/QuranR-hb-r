package com.legalist.mylibrary.managers.mapper


import com.legalist.mylibrary.managers.model.QuranResponse
import org.json.JSONArray
import org.json.JSONObject

class QuranMapper {
    fun mergeSurahs(arabicResponse: QuranResponse, englishResponse: QuranResponse): JSONArray {
        val mergedSurahs = JSONArray()

        for (i in arabicResponse.data.surahs.indices) {
            val arabicSurah = arabicResponse.data.surahs[i]
            val englishSurah = englishResponse.data.surahs[i]

            val mergedSurah = JSONObject()

            mergedSurah.put("number", arabicSurah.number)
            mergedSurah.put("name", arabicSurah.name)
            mergedSurah.put("englishName", englishSurah.englishName)
            mergedSurah.put("englishNameTranslation", englishSurah.englishNameTranslation)
            mergedSurah.put("revelationType", arabicSurah.revelationType)

            val mergedAyahs = JSONArray()
            for (j in arabicSurah.ayahs.indices) {
                val arabicAyah = arabicSurah.ayahs[j]
                val englishAyah = englishSurah.ayahs[j]

                val mergedAyah = JSONObject()
                mergedAyah.put("number", arabicAyah.number)
                mergedAyah.put("textArabic", arabicAyah.text)
                mergedAyah.put("textEnglish", englishAyah.text)
                mergedAyah.put("audio", arabicAyah.audio)

                mergedAyahs.put(mergedAyah)
            }

            mergedSurah.put("ayahs", mergedAyahs)
            mergedSurahs.put(mergedSurah)
        }

        return mergedSurahs
    }
}