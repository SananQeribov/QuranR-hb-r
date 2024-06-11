package com.legalist.mylibrary.managers.model

import com.google.gson.annotations.SerializedName

data class QuranResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: QuranData

)
//https://api.alquran.cloud/v1/quran/ar.alafasy

//https://api.alquran.cloud/v1/quran/en.asad
