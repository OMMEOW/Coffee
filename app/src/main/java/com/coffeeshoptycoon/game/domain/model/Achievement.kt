package com.coffeeshoptycoon.game.domain.model

/** Grouping used purely for the Achievements screen's filter tabs. */
enum class AchievementCategory(val displayKey: String) {
    REVENUE("achievement_category_revenue"),
    CUSTOMERS("achievement_category_customers"),
    DRINKS("achievement_category_drinks"),
    STAFF("achievement_category_staff"),
    MACHINES("achievement_category_machines"),
    RESEARCH("achievement_category_research"),
    PRESTIGE("achievement_category_prestige"),
    STORE("achievement_category_store"),
    REVIEWS("achievement_category_reviews"),
    EVENTS("achievement_category_events"),
    PLAYTIME("achievement_category_playtime"),
    SECRET("achievement_category_secret")
}

/**
 * A single achievement definition (docs/GDD.md §9 — 100+ instances live in
 * AchievementCatalog). Progress is lifetime and never resets on prestige.
 */
data class Achievement(
    val id: String,
    val displayKey: String,
    val descriptionKey: String,
    val category: AchievementCategory,
    val metric: AchievementMetric,
    val targetValue: Double,
    val rewardCash: Double = 0.0,
    val rewardLoyaltyPoints: Double = 0.0,
    val isSecret: Boolean = false
)
