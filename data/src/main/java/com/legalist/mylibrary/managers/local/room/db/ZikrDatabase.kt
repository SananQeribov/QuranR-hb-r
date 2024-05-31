package com.legalist.mylibrary.managers.local.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.legalist.mylibrary.managers.local.entity.Zikr
import com.legalist.mylibrary.managers.local.room.dao.ZikrDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Zikr::class], version = 1)
abstract class ZikrDatabase : RoomDatabase() {
    abstract fun zikrdao(): ZikrDao

    //Singleton
    companion object {
        @Volatile
        private var instance: ZikrDatabase? = null
        private val lock = Any()

        fun getDao() = instance!!.zikrdao()

        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ZikrDatabase::class.java, "ZikrDatabase"

        ).build()
    }

}