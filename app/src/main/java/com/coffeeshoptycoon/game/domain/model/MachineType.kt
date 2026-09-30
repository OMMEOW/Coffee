package com.coffeeshoptycoon.game.domain.model

/**
 * The 10 equipment categories from docs/GDD.md §6. Cost growth is deliberately shallow
 * (1.12-1.18x per level, see docs/GDD.md §14) so every upgrade stays individually meaningful.
 * [appliesTo] is the set of menu items whose brew time/ingredient cost this machine affects;
 * an empty set means the effect is store-wide rather than per-recipe.
 */
enum class MachineType(
    val displayKey: String,
    val baseCost: Double,
    val costGrowthPerLevel: Double,
    val baseSpeedBonusPerLevel: Double,
    val requiredStage: CafeStage,
    val worksWithoutEmployee: Boolean,
    val appliesTo: Set<MenuItemType>
) {
    COFFEE_MACHINE(
        "machine_coffee_machine", baseCost = 150.0, costGrowthPerLevel = 1.14, baseSpeedBonusPerLevel = 0.06,
        requiredStage = CafeStage.STALL, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.ESPRESSO, MenuItemType.AMERICANO)
    ),
    MILK_FROTHER(
        "machine_milk_frother", baseCost = 400.0, costGrowthPerLevel = 1.14, baseSpeedBonusPerLevel = 0.07,
        requiredStage = CafeStage.CORNER_CAFE, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.LATTE, MenuItemType.MOCHA, MenuItemType.ICED_LATTE)
    ),
    GRINDER(
        "machine_grinder", baseCost = 600.0, costGrowthPerLevel = 1.13, baseSpeedBonusPerLevel = 0.05,
        requiredStage = CafeStage.NEIGHBORHOOD_SHOP, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.ESPRESSO, MenuItemType.AMERICANO, MenuItemType.COLD_BREW, MenuItemType.PREMIUM_COFFEE)
    ),
    ICE_MACHINE(
        "machine_ice_machine", baseCost = 900.0, costGrowthPerLevel = 1.13, baseSpeedBonusPerLevel = 0.06,
        requiredStage = CafeStage.NEIGHBORHOOD_SHOP, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.COLD_BREW, MenuItemType.ICED_LATTE, MenuItemType.SMOOTHIE)
    ),
    BLENDER(
        "machine_blender", baseCost = 1_200.0, costGrowthPerLevel = 1.14, baseSpeedBonusPerLevel = 0.08,
        requiredStage = CafeStage.POPULAR_CAFE, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.SMOOTHIE)
    ),
    OVEN(
        "machine_oven", baseCost = 1_500.0, costGrowthPerLevel = 1.14, baseSpeedBonusPerLevel = 0.07,
        requiredStage = CafeStage.POPULAR_CAFE, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.CAKE, MenuItemType.COOKIE, MenuItemType.DONUT, MenuItemType.SANDWICH)
    ),
    PREMIUM_MACHINE(
        "machine_premium_machine", baseCost = 5_000.0, costGrowthPerLevel = 1.15, baseSpeedBonusPerLevel = 0.06,
        requiredStage = CafeStage.LOCAL_CHAIN, worksWithoutEmployee = false,
        appliesTo = setOf(MenuItemType.PREMIUM_COFFEE, MenuItemType.SEASONAL_DRINK)
    ),
    SELF_CHECKOUT(
        "machine_self_checkout", baseCost = 8_000.0, costGrowthPerLevel = 1.15, baseSpeedBonusPerLevel = 0.10,
        requiredStage = CafeStage.CITY_FAVORITE, worksWithoutEmployee = true,
        appliesTo = emptySet()
    ),
    INDUSTRIAL_MACHINE(
        "machine_industrial_machine", baseCost = 25_000.0, costGrowthPerLevel = 1.16, baseSpeedBonusPerLevel = 0.09,
        requiredStage = CafeStage.CITY_FAVORITE, worksWithoutEmployee = false,
        appliesTo = emptySet()
    ),
    ROBOT_BARISTA(
        "machine_robot_barista", baseCost = 100_000.0, costGrowthPerLevel = 1.18, baseSpeedBonusPerLevel = 0.12,
        requiredStage = CafeStage.REGIONAL_BRAND, worksWithoutEmployee = true,
        appliesTo = emptySet()
    );

    fun costForLevel(level: Int): Double = baseCost * Math.pow(costGrowthPerLevel, (level - 1).toDouble())
}
