package com.core.data.local.preferences

import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.core.data.response.login.DataLoginResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) :UserPreferences{

    private val Context.dataStore by preferencesDataStore(name = "preferences")

    override suspend fun getToken(): String {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }.first()
    }

    override suspend fun login(response: DataLoginResponse) {
        context.dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
            preferences[TOKEN_KEY] = response.token
        }
    }

    override suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun storeToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun sessionLogin(token: String) {
        context.dataStore.edit {preference ->
            preference[STATE_KEY] = false
            preference[TOKEN_KEY] = ""
        }
    }

    companion object {
        private val STATE_KEY = booleanPreferencesKey("state")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
    }
}