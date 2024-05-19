package com.ders.domain.model

data class QuranResponse(
    val code: Int,
    val status: String,
    val data: List<Surah>
)
