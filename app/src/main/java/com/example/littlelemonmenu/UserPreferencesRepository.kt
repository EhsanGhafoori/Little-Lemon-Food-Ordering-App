package com.example.littlelemonmenu

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.userPreferences by preferencesDataStore(name = "little_lemon_user")

class UserPreferencesRepository(private val context: Context) {

    private object Keys {
        val FIRST_NAME = stringPreferencesKey("first_name")
        val LAST_NAME = stringPreferencesKey("last_name")
        val EMAIL = stringPreferencesKey("email")
        val PHONE = stringPreferencesKey("phone")
        val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
    }

    val firstName: Flow<String> = context.userPreferences.data.map { prefs -> prefs[Keys.FIRST_NAME] ?: "" }
    val lastName: Flow<String> = context.userPreferences.data.map { prefs -> prefs[Keys.LAST_NAME] ?: "" }
    val email: Flow<String> = context.userPreferences.data.map { prefs -> prefs[Keys.EMAIL] ?: "" }
    val phone: Flow<String> = context.userPreferences.data.map { prefs -> prefs[Keys.PHONE] ?: "" }

    suspend fun isOnboardingCompleted(): Boolean =
        context.userPreferences.data.map { prefs -> prefs[Keys.ONBOARDING_DONE] == true }.first()

    suspend fun saveRegistration(firstName: String, lastName: String, email: String) {
        context.userPreferences.edit { prefs ->
            prefs[Keys.FIRST_NAME] = firstName.trim()
            prefs[Keys.LAST_NAME] = lastName.trim()
            prefs[Keys.EMAIL] = email.trim()
            prefs[Keys.ONBOARDING_DONE] = true
        }
    }

    suspend fun savePhone(phone: String) {
        context.userPreferences.edit { prefs ->
            prefs[Keys.PHONE] = phone.trim()
        }
    }

    suspend fun clearAll() {
        context.userPreferences.edit { prefs ->
            prefs.remove(Keys.FIRST_NAME)
            prefs.remove(Keys.LAST_NAME)
            prefs.remove(Keys.EMAIL)
            prefs.remove(Keys.PHONE)
            prefs[Keys.ONBOARDING_DONE] = false
        }
    }
}
