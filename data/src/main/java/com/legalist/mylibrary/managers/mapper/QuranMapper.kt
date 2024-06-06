package com.legalist.mylibrary.managers.mapper


import android.util.Log
import com.legalist.mylibrary.managers.model.QuranData
import com.legalist.mylibrary.managers.model.QuranResponse
import org.json.JSONArray
import org.json.JSONObject



import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types




class QuranMapper {
    private val moshi = Moshi.Builder().build()
    //private val quranResponseType = Types.newParameterizedType(QuranResponse::class.java, QuranData::class.java)

    fun convertToJSONArray(arabicResponse: QuranResponse): JSONArray {
        val adapter: JsonAdapter<QuranData> = moshi.adapter(QuranData::class.java)
        val json = adapter.toJson(arabicResponse.data)
        return JSONArray(json)
    }
}

