package com.ipoy.android_dasar_v3.ui.profile.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class ThemePref private constructor(private val dataStore: DataStore<Preferences>){
    private val mode = booleanPreferencesKey("theme_setting")

    fun get(): Flow<Boolean>{
        return dataStore.data.map { preferences ->
            preferences[mode] ?: false
        }
    }

    suspend fun save(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[mode] = isDarkModeActive
        }
    }


    companion object {
        @Volatile
        private var inst: ThemePref? = null

        fun get(dataStore: DataStore<Preferences>): ThemePref {
            return inst ?: synchronized(this) {
                val instance = ThemePref(dataStore)
                inst = instance
                instance
            }
        }
    }

}