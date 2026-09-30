package com.coffeeshoptycoon.game.domain.model

/**
 * A permanent, Roasted-Bean-funded upgrade (docs/GDD.md §8). Unlike research, these
 * survive prestige — [effectPerLevel] is applied once per owned level, up to [maxLevel].
 */
data class PrestigeUpgrade(
    val id: String,
    val displayKey: String,
    val descriptionKey: String,
    val beanCostBase: Double,
    val beanCostGrowthPerLevel: Double,
    val maxLevel: Int,
    val effectPerLevel: GameEffect
) {
    fun costForLevel(level: Int): Double = beanCostBase * Math.pow(beanCostGrowthPerLevel, (level - 1).toDouble())
}
