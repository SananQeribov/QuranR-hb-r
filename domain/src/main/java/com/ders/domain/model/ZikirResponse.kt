package com.ders.domain.model

data class ZikirResponse(
    val code: Int,
    val data: List<Data>,
    val status: String
)