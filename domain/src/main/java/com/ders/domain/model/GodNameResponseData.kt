package com.ders.domain.model

import com.google.gson.annotations.SerializedName

data class GodNameResponseData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<GodNameModel>
)
