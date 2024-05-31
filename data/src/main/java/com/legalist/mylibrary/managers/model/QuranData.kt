package com.legalist.mylibrary.managers.model

import com.google.gson.annotations.SerializedName

data class QuranData(
    @SerializedName("surahs") val surahs: List<Surah>
)