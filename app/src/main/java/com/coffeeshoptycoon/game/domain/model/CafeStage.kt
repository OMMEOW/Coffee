package com.coffeeshoptycoon.game.domain.model

/**
 * Narrative/economic tier of the player's business, gated by lifetime cash earned
 * this prestige run. Driving fields for [CafeStage] are documented in docs/GDD.md §4.
 */
enum class CafeStage(val order: Int, val lifetimeRevenueThreshold: Double) {
    STALL(0, 0.0),
    CORNER_CAFE(1, 5_000.0),
    NEIGHBORHOOD_SHOP(2, 50_000.0),
    POPULAR_CAFE(3, 250_000.0),
    LOCAL_CHAIN(4, 1_000_000.0),
    CITY_FAVORITE(5, 5_000_000.0),
    REGIONAL_BRAND(6, 25_000_000.0),
    FRANCHISE_EMPIRE(7, 100_000_000.0);

    companion object {
        fun forLifetimeRevenue(revenue: Double): CafeStage =
            entries.lastOrNull { revenue >= it.lifetimeRevenueThreshold } ?: STALL
    }
}
