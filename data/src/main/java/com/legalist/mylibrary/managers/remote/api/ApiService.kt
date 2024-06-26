package com.legalist.mylibrary.managers.remote.api

import com.ders.domain.model.QuranResponse2
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/surah")
    suspend fun getSurahs(): Response<QuranResponse2>
}