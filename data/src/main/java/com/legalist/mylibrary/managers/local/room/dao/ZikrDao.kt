package com.legalist.mylibrary.managers.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.legalist.mylibrary.managers.local.entity.Zikr
import kotlinx.coroutines.flow.Flow

@Dao
interface ZikrDao {
    @Insert
    fun insertAll( zikr: List<Zikr>)

    @Query("SELECT * FROM zikirs")
    fun getdataAll(): Flow<List<Zikr>>

    @Delete
    fun removeAll(vararg zikr: Zikr)
}