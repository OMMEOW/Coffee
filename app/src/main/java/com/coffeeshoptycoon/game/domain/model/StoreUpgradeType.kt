package com.coffeeshoptycoon.game.domain.model

/**
 * The 10 store decor/expansion upgrades from docs/GDD.md §6. [capacityBonusPerLevel]
 * raises how many customers can queue simultaneously; [moodBonusPerLevel] raises the
 * baseline mood (and thus tips/reviews) of every customer served. [maxLevel] of 1 makes
 * an upgrade a one-time purchase (e.g. Drive-Through, Second Floor) rather than a stackable one.
 */
enum class StoreUpgradeType(
    val displayKey: String,
    val baseCost: Double,
    val costGrowthPerLevel: Double,
    val capacityBonusPerLevel: Int,
    val moodBonusPerLevel: Double,
    val maxLevel: Int,
    val requiredStage: CafeStage
) {
    TABLES("upgrade_tables", baseCost = 300.0, costGrowthPerLevel = 1.15, capacityBonusPerLevel = 2, moodBonusPerLevel = 0.010, maxLevel = 10, requiredStage = CafeStage.NEIGHBORHOOD_SHOP),
    DECORATION("upgrade_decoration", baseCost = 500.0, costGrowthPerLevel = 1.15, capacityBonusPerLevel = 0, moodBonusPerLevel = 0.030, maxLevel = 10, requiredStage = CafeStage.NEIGHBORHOOD_SHOP),
    MUSIC("upgrade_music", baseCost = 400.0, costGrowthPerLevel = 1.15, capacityBonusPerLevel = 0, moodBonusPerLevel = 0.025, maxLevel = 10, requiredStage = CafeStage.CORNER_CAFE),
    LIGHTING("upgrade_lighting", baseCost = 350.0, costGrowthPerLevel = 1.15, capacityBonusPerLevel = 0, moodBonusPerLevel = 0.020, maxLevel = 10, requiredStage = CafeStage.CORNER_CAFE),
    PLANTS("upgrade_plants", baseCost = 250.0, costGrowthPerLevel = 1.14, capacityBonusPerLevel = 0, moodBonusPerLevel = 0.015, maxLevel = 10, requiredStage = CafeStage.CORNER_CAFE),
    WALLS("upgrade_walls", baseCost = 800.0, costGrowthPerLevel = 1.16, capacityBonusPerLevel = 0, moodBonusPerLevel = 0.020, maxLevel = 5, requiredStage = CafeStage.POPULAR_CAFE),
    FLOOR("upgrade_floor", baseCost = 600.0, costGrowthPerLevel = 1.15, capacityBonusPerLevel = 0, moodBonusPerLevel = 0.020, maxLevel = 5, requiredStage = CafeStage.POPULAR_CAFE),
    OUTDOOR_SEATING("upgrade_outdoor_seating", baseCost = 5_000.0, costGrowthPerLevel = 1.20, capacityBonusPerLevel = 10, moodBonusPerLevel = 0.020, maxLevel = 3, requiredStage = CafeStage.LOCAL_CHAIN),
    DRIVE_THROUGH("upgrade_drive_through", baseCost = 20_000.0, costGrowthPerLevel = 1.0, capacityBonusPerLevel = 15, moodBonusPerLevel = 0.0, maxLevel = 1, requiredStage = CafeStage.CITY_FAVORITE),
    SECOND_FLOOR("upgrade_second_floor", baseCost = 80_000.0, costGrowthPerLevel = 1.0, capacityBonusPerLevel = 25, moodBonusPerLevel = 0.050, maxLevel = 1, requiredStage = CafeStage.REGIONAL_BRAND);

    fun costForLevel(level: Int): Double = baseCost * Math.pow(costGrowthPerLevel, (level - 1).toDouble())
}
