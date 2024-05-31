package com.legalist.mylibrary.managers.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zikirs")
data class Zikr(
    @ColumnInfo("arabicName")
    val arabicName: String,
    @ColumnInfo("englishTranslation")
    val englishTranslation: String,
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("transliteration")
    val transliteration: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid = 0
}
