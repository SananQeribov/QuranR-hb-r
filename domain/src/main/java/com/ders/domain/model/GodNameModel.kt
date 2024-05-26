package com.ders.domain.model

import com.google.gson.annotations.SerializedName


data class GodNameModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("arabicName")
    val arabicName: String,
    @SerializedName("transliteration")
    val transliteration: String,
    @SerializedName("englishTranslation")
    val englishTranslation: String
)