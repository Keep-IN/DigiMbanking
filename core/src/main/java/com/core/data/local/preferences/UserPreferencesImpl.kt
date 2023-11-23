package com.core.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
//class UserPreferencesImpl @Inject constructor(
//    @ApplicationContext private val context: Context
//) : UserPreferences {
//
//    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
//        "preferences",
//        Context.MODE_PRIVATE
//    )
//
//    override suspend fun getIdd(): Int {
//        return sharedPreferences.getInt(USER_ID_KEY, 0)
//    }
//
//    override suspend fun setId(id: Int) {
//        sharedPreferences.edit().putInt(USER_ID_KEY, id).apply()
//    }
//
//    companion object {
//        private const val USER_ID_KEY = "user_id"
//    }
//}

class UserPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) :UserPreferences {

    private val Context.dataStore by preferencesDataStore(name = "preferences")
    override suspend fun getIdd(): Int {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY] ?: 0
        }.first()
    }
    override suspend fun setId(id: Int) {
        context.dataStore.edit {preferences ->
            preferences[USER_ID_KEY] = id
        }
    }
    companion object {
        private val USER_ID_KEY = intPreferencesKey("user_id")
    }


}