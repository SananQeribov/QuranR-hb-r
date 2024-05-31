package com.legalist.mylibrary.managers.db


import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey



import android.content.Context

import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONArray

class QuranDataStore(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "quran_data_store")

    suspend fun saveQuranData(data: JSONArray) {
        val key = preferencesKey<String>("key_quran_data")
        dataStore.edit { preferences ->
            preferences[key] = data.toString()
        }
    }

    suspend fun getQuranData(): JSONArray? {
        val key = preferencesKey<String>("key_quran_data")
        return dataStore.data.map { preferences ->
            val jsonString = preferences[key] ?: return@map null
            JSONArray(jsonString)
        }.first()
    }

    companion object {
        private val KEY_QURAN_DATA = preferencesKey<String>("key_quran_data")
    }
}
