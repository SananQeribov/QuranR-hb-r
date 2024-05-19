package com.legalist.mylibrary.managers.api

import com.ders.domain.model.QuranResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/surah")
    suspend fun getSurahs(): Response<QuranResponse>
}