package com.coffeeshoptycoon.game.domain.model

/**
 * Calendar-scheduled events from docs/GDD.md §12. [startMonth]/[startDay] use a
 * 1-indexed Gregorian calendar; scheduling/rollover logic lives in the engine's
 * EventScheduler (Milestone 16), not here.
 */
enum class SeasonalEventType(val displayKey: String, val startMonth: Int, val startDay: Int, val durationDays: Int) {
    COFFEE_FESTIVAL("event_coffee_festival", startMonth = 9, startDay = 1, durationDays = 10),
    HALLOWEEN("event_halloween", startMonth = 10, startDay = 24, durationDays = 8),
    BLACK_FRIDAY("event_black_friday", startMonth = 11, startDay = 24, durationDays = 4),
    CHRISTMAS("event_christmas", startMonth = 12, startDay = 15, durationDays = 17),
    SUMMER("event_summer", startMonth = 6, startDay = 15, durationDays = 30)
}
