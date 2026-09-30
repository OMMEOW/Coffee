package com.coffeeshoptycoon.game.domain.model

/** Lifetime counters that never reset on prestige — backs the Statistics screen. */
data class GameStatistics(
    val totalCashEarnedAllTime: Double = 0.0,
    val totalCustomersServedAllTime: Long = 0,
    val drinksSoldByType: Map<MenuItemType, Long> = emptyMap(),
    val totalPlaytimeSeconds: Long = 0,
    val totalPrestiges: Int = 0,
    val currentPerfectReviewStreak: Int = 0,
    val longestPerfectReviewStreak: Int = 0,
    val eventsParticipated: Int = 0
) {
    val totalDrinksSoldAllTime: Long get() = drinksSoldByType.values.sum()
}
