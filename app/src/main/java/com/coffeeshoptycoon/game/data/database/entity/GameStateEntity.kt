package com.coffeeshoptycoon.game.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffeeshoptycoon.game.domain.model.ActiveRandomEvent
import com.coffeeshoptycoon.game.domain.model.ActiveSeasonalEvent
import com.coffeeshoptycoon.game.domain.model.IngredientType
import com.coffeeshoptycoon.game.domain.model.MenuItemType

/**
 * Singleton row (always [id] == 0) holding every scalar/collection field of the current
 * run's [com.coffeeshoptycoon.game.domain.model.GameState] that isn't already its own
 * table (employees, machines, and store upgrades are normalized — see their own entities).
 */
@Entity(tableName = "game_state")
data class GameStateEntity(
    @PrimaryKey val id: Int = SINGLETON_ID,
    val cash: Double,
    val roastedBeans: Double,
    val loyaltyPoints: Double,
    val eventTokens: Double,
    val lifetimeCashEarnedThisRun: Double,
    val reputation: Double,
    val unlockedMenuItems: Set<MenuItemType>,
    val inventory: Map<IngredientType, Int>,
    val completedResearchIds: Set<String>,
    val prestigeUpgradeLevels: Map<String, Int>,
    val unlockedAchievementIds: Set<String>,
    val activeSeasonalEvent: ActiveSeasonalEvent?,
    val activeRandomEvents: List<ActiveRandomEvent>,
    val dailyRewardStreak: Int,
    val dailyRewardCumulativeDays: Int,
    val lastDailyRewardClaimEpochDay: Long,
    val prestigeCount: Int,
    val lastActiveTimestamp: Long
) {
    companion object {
        const val SINGLETON_ID = 0
    }
}
