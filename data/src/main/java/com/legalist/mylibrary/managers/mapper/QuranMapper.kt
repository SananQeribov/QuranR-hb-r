package com.legalist.mylibrary.managers.mapper

import com.legalist.mylibrary.managers.model.QuranResponse
import org.json.JSONArray
import org.json.JSONObject

class QuranMapper {
    fun convertToJSONArray(arabicResponse: QuranResponse): JSONArray {
        return JSONArray().apply {
            arabicResponse.data.surahs.map { surah ->
                JSONObject().apply {
                    put("number", surah.number)
                    put("name", surah.name)
                    put("englishName", surah.englishName)
                    put("englishNameTranslation", surah.englishNameTranslation)
                    put("revelationType", surah.revelationType)

                    val ayahsArray = JSONArray()
                    surah.ayahs.map { ayah ->
                        JSONObject().apply {
                            put("number", ayah.number)
                            put("textArabic", ayah.textArabic)
                            put("textEnglish", ayah.textEnglish)
                            put("audio", ayah.audio)
                        }
                    }.forEach { ayahObject ->
                        ayahsArray.put(ayahObject)
                    }

                    put("ayahs", ayahsArray)
                }
            }.forEach { surahObject ->
                put(surahObject)
            }
        }
    }
}
