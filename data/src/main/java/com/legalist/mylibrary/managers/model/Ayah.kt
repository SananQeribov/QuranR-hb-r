package com.legalist.mylibrary.managers.model

import com.google.gson.annotations.SerializedName

data class Ayah(
    @SerializedName("number") val number: Int,
    @SerializedName("textArabic") val textArabic: String,
    @SerializedName("textEnglish") val textEnglish: String,
    @SerializedName("audio") val audio: String
)