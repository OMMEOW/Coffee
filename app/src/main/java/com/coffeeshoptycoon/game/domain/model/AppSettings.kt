package com.coffeeshoptycoon.game.domain.model

/** User-configurable preferences, persisted via DataStore (docs/GDD.md §"Settings"). */
data class AppSettings(
    val musicEnabled: Boolean = true,
    val musicVolume: Float = 0.7f,
    val soundEnabled: Boolean = true,
    val soundVolume: Float = 1.0f,
    val notificationsEnabled: Boolean = true,
    val showFpsCounter: Boolean = false,
    val batterySaverEnabled: Boolean = false,
    /** null == follow system light/dark setting. */
    val darkThemeOverride: Boolean? = null,
    /** BCP-47 tag, or "system" to follow device locale (localization-ready, see docs/GDD.md). */
    val languageTag: String = "system"
)
