package com.ders.domain.model

import androidx.room.Entity

@Entity
data class Data(
    val arabicName: String,
    val englishTranslation: String,
    val id: Int,
    val transliteration: String
)