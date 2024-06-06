package com.legalist.mylibrary.managers.api

import com.legalist.mylibrary.managers.model.QuranResponse
import retrofit2.http.GET

interface QuranApi {
    @GET("/host_api/surah_ayah.json")
    suspend fun getQuranInArabic(): QuranResponse

//    @GET("/host_api/en.asad.json")
//    suspend fun getQuranInEnglish(): QuranResponse
}