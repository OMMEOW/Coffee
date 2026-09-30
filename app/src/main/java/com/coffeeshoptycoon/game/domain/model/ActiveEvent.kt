package com.coffeeshoptycoon.game.domain.model

/** A currently-running seasonal event instance. */
data class ActiveSeasonalEvent(
    val type: SeasonalEventType,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val eventTokensEarned: Double = 0.0
)

/** A currently-running (or just-resolved) random event instance. */
data class ActiveRandomEvent(
    val id: String,
    val type: RandomEventType,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val resolved: Boolean = false,
    val wasHandledSuccessfully: Boolean = false
)
