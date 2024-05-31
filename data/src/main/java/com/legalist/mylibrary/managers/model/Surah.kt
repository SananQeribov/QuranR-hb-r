package com.legalist.mylibrary.managers.model

import com.google.gson.annotations.SerializedName

data class Surah(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
    @SerializedName("englishName") val englishName: String,
    @SerializedName("englishNameTranslation") val englishNameTranslation: String,
    @SerializedName("revelationType") val revelationType: String,
    @SerializedName("ayahs") val ayahs: List<Ayah>
)