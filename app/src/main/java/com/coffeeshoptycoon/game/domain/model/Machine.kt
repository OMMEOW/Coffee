package com.coffeeshoptycoon.game.domain.model

/** A single owned instance of a [MachineType] at a given upgrade [level]. */
data class Machine(
    val id: String,
    val type: MachineType,
    val level: Int = 1
) {
    val speedMultiplier: Double get() = 1.0 + type.baseSpeedBonusPerLevel * (level - 1)
    val nextUpgradeCost: Double get() = type.costForLevel(level + 1)
}
