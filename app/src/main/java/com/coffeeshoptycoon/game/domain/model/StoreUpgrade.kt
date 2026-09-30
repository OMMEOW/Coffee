package com.coffeeshoptycoon.game.domain.model

/** A store decor/expansion upgrade owned at a given [level] (0 == not yet purchased). */
data class StoreUpgrade(
    val type: StoreUpgradeType,
    val level: Int = 0
) {
    val isMaxed: Boolean get() = level >= type.maxLevel
    val capacityBonus: Int get() = type.capacityBonusPerLevel * level
    val moodBonus: Double get() = type.moodBonusPerLevel * level
    val nextUpgradeCost: Double? get() = if (isMaxed) null else type.costForLevel(level + 1)
}
