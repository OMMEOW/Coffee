package com.coffeeshoptycoon.game.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffeeshoptycoon.game.domain.model.MenuItemType

/** Singleton row (always [id] == 0) — lifetime stats that survive prestige. */
@Entity(tableName = "statistics")
data class StatisticsEntity(
    @PrimaryKey val id: Int = SINGLETON_ID,
    val totalCashEarnedAllTime: Double,
    val totalCustomersServedAllTime: Long,
    val drinksSoldByType: Map<MenuItemType, Long>,
    val totalPlaytimeSeconds: Long,
    val totalPrestiges: Int,
    val currentPerfectReviewStreak: Int,
    val longestPerfectReviewStreak: Int,
    val eventsParticipated: Int
) {
    companion object {
        const val SINGLETON_ID = 0
    }
}
