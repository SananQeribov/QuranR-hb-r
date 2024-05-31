package com.ders.domain.model

data class QuranResponse2(
    val code: Int,
    val status: String,
    val data: List<Surah>
)
