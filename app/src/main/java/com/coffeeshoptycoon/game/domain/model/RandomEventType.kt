package com.coffeeshoptycoon.game.domain.model

/**
 * Low-probability, single-serving events from docs/GDD.md §12. [baseChancePerHour] is
 * rolled once per in-game hour of active play by the engine's RandomEventEngine and is
 * reduced by Security staff / research (see [GameEffect.NegativeEventChanceMultiplier]).
 */
enum class RandomEventType(
    val displayKey: String,
    val baseChancePerHour: Double,
    val durationMinutes: Int,
    val isNegative: Boolean
) {
    FOOD_INSPECTOR("event_food_inspector", baseChancePerHour = 0.02, durationMinutes = 10, isNegative = true),
    CELEBRITY_VISIT("event_celebrity_visit", baseChancePerHour = 0.005, durationMinutes = 5, isNegative = false),
    MACHINE_BREAKDOWN("event_machine_breakdown", baseChancePerHour = 0.015, durationMinutes = 20, isNegative = true),
    POWER_OUTAGE("event_power_outage", baseChancePerHour = 0.01, durationMinutes = 15, isNegative = true),
    RAIN("event_rain", baseChancePerHour = 0.03, durationMinutes = 60, isNegative = false),
    INFLUENCER_REVIEW("event_influencer_review", baseChancePerHour = 0.008, durationMinutes = 5, isNegative = false),
    COFFEE_BEAN_SHORTAGE("event_coffee_bean_shortage", baseChancePerHour = 0.012, durationMinutes = 30, isNegative = true)
}
