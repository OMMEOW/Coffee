package com.coffeeshoptycoon.game.domain.repository

import com.coffeeshoptycoon.game.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<AppSettings>
    suspend fun setMusicEnabled(enabled: Boolean)
    suspend fun setMusicVolume(volume: Float)
    suspend fun setSoundEnabled(enabled: Boolean)
    suspend fun setSoundVolume(volume: Float)
    suspend fun setNotificationsEnabled(enabled: Boolean)
    suspend fun setShowFpsCounter(enabled: Boolean)
    suspend fun setBatterySaverEnabled(enabled: Boolean)
    suspend fun setDarkThemeOverride(override: Boolean?)
    suspend fun setLanguageTag(tag: String)
}
