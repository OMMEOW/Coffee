package com.coffeeshoptycoon.game.data.repository

import com.coffeeshoptycoon.game.data.datastore.SettingsDataSource
import com.coffeeshoptycoon.game.domain.model.AppSettings
import com.coffeeshoptycoon.game.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataSource: SettingsDataSource
) : SettingsRepository {

    override val settings: Flow<AppSettings> = dataSource.settings

    override suspend fun setMusicEnabled(enabled: Boolean) = dataSource.setMusicEnabled(enabled)
    override suspend fun setMusicVolume(volume: Float) = dataSource.setMusicVolume(volume)
    override suspend fun setSoundEnabled(enabled: Boolean) = dataSource.setSoundEnabled(enabled)
    override suspend fun setSoundVolume(volume: Float) = dataSource.setSoundVolume(volume)
    override suspend fun setNotificationsEnabled(enabled: Boolean) = dataSource.setNotificationsEnabled(enabled)
    override suspend fun setShowFpsCounter(enabled: Boolean) = dataSource.setShowFpsCounter(enabled)
    override suspend fun setBatterySaverEnabled(enabled: Boolean) = dataSource.setBatterySaverEnabled(enabled)
    override suspend fun setDarkThemeOverride(override: Boolean?) = dataSource.setDarkThemeOverride(override)
    override suspend fun setLanguageTag(tag: String) = dataSource.setLanguageTag(tag)
}
