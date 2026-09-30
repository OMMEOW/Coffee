package com.coffeeshoptycoon.game.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.coffeeshoptycoon.game.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Thin, typed wrapper around the raw Preferences DataStore for [AppSettings]. */
class SettingsDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val MUSIC_ENABLED = booleanPreferencesKey("music_enabled")
        val MUSIC_VOLUME = floatPreferencesKey("music_volume")
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val SOUND_VOLUME = floatPreferencesKey("sound_volume")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val SHOW_FPS_COUNTER = booleanPreferencesKey("show_fps_counter")
        val BATTERY_SAVER_ENABLED = booleanPreferencesKey("battery_saver_enabled")
        val DARK_THEME_OVERRIDE = stringPreferencesKey("dark_theme_override")
        val LANGUAGE_TAG = stringPreferencesKey("language_tag")
    }

    val settings: Flow<AppSettings> = dataStore.data.map { prefs ->
        val default = AppSettings()
        AppSettings(
            musicEnabled = prefs[Keys.MUSIC_ENABLED] ?: default.musicEnabled,
            musicVolume = prefs[Keys.MUSIC_VOLUME] ?: default.musicVolume,
            soundEnabled = prefs[Keys.SOUND_ENABLED] ?: default.soundEnabled,
            soundVolume = prefs[Keys.SOUND_VOLUME] ?: default.soundVolume,
            notificationsEnabled = prefs[Keys.NOTIFICATIONS_ENABLED] ?: default.notificationsEnabled,
            showFpsCounter = prefs[Keys.SHOW_FPS_COUNTER] ?: default.showFpsCounter,
            batterySaverEnabled = prefs[Keys.BATTERY_SAVER_ENABLED] ?: default.batterySaverEnabled,
            darkThemeOverride = prefs[Keys.DARK_THEME_OVERRIDE]?.toBooleanStrictOrNull(),
            languageTag = prefs[Keys.LANGUAGE_TAG] ?: default.languageTag
        )
    }

    suspend fun setMusicEnabled(enabled: Boolean): Unit {
        dataStore.edit { it[Keys.MUSIC_ENABLED] = enabled }
    }

    suspend fun setMusicVolume(volume: Float): Unit {
        dataStore.edit { it[Keys.MUSIC_VOLUME] = volume.coerceIn(0f, 1f) }
    }

    suspend fun setSoundEnabled(enabled: Boolean): Unit {
        dataStore.edit { it[Keys.SOUND_ENABLED] = enabled }
    }

    suspend fun setSoundVolume(volume: Float): Unit {
        dataStore.edit { it[Keys.SOUND_VOLUME] = volume.coerceIn(0f, 1f) }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean): Unit {
        dataStore.edit { it[Keys.NOTIFICATIONS_ENABLED] = enabled }
    }

    suspend fun setShowFpsCounter(enabled: Boolean): Unit {
        dataStore.edit { it[Keys.SHOW_FPS_COUNTER] = enabled }
    }

    suspend fun setBatterySaverEnabled(enabled: Boolean): Unit {
        dataStore.edit { it[Keys.BATTERY_SAVER_ENABLED] = enabled }
    }

    suspend fun setDarkThemeOverride(override: Boolean?): Unit {
        dataStore.edit {
            if (override == null) it.remove(Keys.DARK_THEME_OVERRIDE) else it[Keys.DARK_THEME_OVERRIDE] = override.toString()
        }
    }

    suspend fun setLanguageTag(tag: String): Unit {
        dataStore.edit { it[Keys.LANGUAGE_TAG] = tag }
    }
}
