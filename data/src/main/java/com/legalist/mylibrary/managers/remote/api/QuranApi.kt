package com.legalist.mylibrary.managers.api

import com.legalist.mylibrary.managers.model.QuranResponse
import retrofit2.http.GET

interface QuranApi {
    @GET("ar.alafasy")
    suspend fun getQuranInArabic(): QuranResponse

    @GET("en.asad")
    suspend fun getQuranInEnglish(): QuranResponse
}