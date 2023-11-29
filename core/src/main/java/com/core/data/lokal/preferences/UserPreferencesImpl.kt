package com.core.data.lokal.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.core.data.response.auth.createRekening.email.DataOtp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreferences {

    private val Context.dataStore by preferencesDataStore(name = "preferences")
    override suspend fun getUser(): Int {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY] ?: 0
        }.first()
    }

    override suspend fun getCard(): Int {
        return context.dataStore.data.map { preferences ->
            preferences[CARD_ID_KEY] ?: 0
        }.first()
    }

    override suspend fun setId(id: Int) {
        context.dataStore.edit {preferences ->
            preferences[USER_ID_KEY] = id
        }
    }

    override suspend fun setCardId(id: Int) {
        context.dataStore.edit { preferences ->
            preferences[CARD_ID_KEY] = id
        }
    }

    companion object {
        private val USER_ID_KEY = intPreferencesKey("user_id")
        private val CARD_ID_KEY = intPreferencesKey("card_id")
    }
}