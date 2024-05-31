package com.legalist.mylibrary.managers.model

import com.google.gson.annotations.SerializedName

data class Ayah(
    @SerializedName("number") val number: Int,
    @SerializedName("text") val text: String,
    @SerializedName("audio") val audio: String
)