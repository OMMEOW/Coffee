package com.coffeeshoptycoon.game.domain.model

/**
 * The 14 menu categories from docs/GDD.md §6. [basePrice] and [baseIngredientCost] are
 * pre-multiplier values in dollars; [baseBrewSeconds] is the prep time on a level-1
 * Coffee Machine with an untrained employee. [unlockCost] is one-time cash spent to
 * add the item to the active menu; [requiredStage] additionally gates availability.
 */
enum class MenuItemType(
    val displayKey: String,
    val basePrice: Double,
    val baseIngredientCost: Double,
    val baseBrewSeconds: Double,
    val unlockCost: Double,
    val requiredStage: CafeStage
) {
    ESPRESSO("menu_espresso", basePrice = 3.50, baseIngredientCost = 0.60, baseBrewSeconds = 8.0, unlockCost = 0.0, requiredStage = CafeStage.STALL),
    LATTE("menu_latte", basePrice = 4.50, baseIngredientCost = 0.90, baseBrewSeconds = 12.0, unlockCost = 500.0, requiredStage = CafeStage.CORNER_CAFE),
    MOCHA("menu_mocha", basePrice = 5.00, baseIngredientCost = 1.10, baseBrewSeconds = 14.0, unlockCost = 750.0, requiredStage = CafeStage.CORNER_CAFE),
    AMERICANO("menu_americano", basePrice = 3.75, baseIngredientCost = 0.55, baseBrewSeconds = 9.0, unlockCost = 1_500.0, requiredStage = CafeStage.NEIGHBORHOOD_SHOP),
    COLD_BREW("menu_cold_brew", basePrice = 5.25, baseIngredientCost = 0.80, baseBrewSeconds = 6.0, unlockCost = 2_500.0, requiredStage = CafeStage.NEIGHBORHOOD_SHOP),
    TEA("menu_tea", basePrice = 3.25, baseIngredientCost = 0.45, baseBrewSeconds = 7.0, unlockCost = 8_000.0, requiredStage = CafeStage.POPULAR_CAFE),
    SMOOTHIE("menu_smoothie", basePrice = 6.00, baseIngredientCost = 1.60, baseBrewSeconds = 16.0, unlockCost = 10_000.0, requiredStage = CafeStage.POPULAR_CAFE),
    CAKE("menu_cake", basePrice = 5.50, baseIngredientCost = 1.80, baseBrewSeconds = 20.0, unlockCost = 15_000.0, requiredStage = CafeStage.POPULAR_CAFE),
    COOKIE("menu_cookie", basePrice = 2.50, baseIngredientCost = 0.40, baseBrewSeconds = 5.0, unlockCost = 40_000.0, requiredStage = CafeStage.LOCAL_CHAIN),
    DONUT("menu_donut", basePrice = 3.00, baseIngredientCost = 0.55, baseBrewSeconds = 6.0, unlockCost = 55_000.0, requiredStage = CafeStage.LOCAL_CHAIN),
    SANDWICH("menu_sandwich", basePrice = 7.50, baseIngredientCost = 2.40, baseBrewSeconds = 25.0, unlockCost = 200_000.0, requiredStage = CafeStage.CITY_FAVORITE),
    PREMIUM_COFFEE("menu_premium_coffee", basePrice = 9.00, baseIngredientCost = 2.00, baseBrewSeconds = 18.0, unlockCost = 300_000.0, requiredStage = CafeStage.CITY_FAVORITE),
    ICED_LATTE("menu_iced_latte", basePrice = 5.75, baseIngredientCost = 1.00, baseBrewSeconds = 10.0, unlockCost = 1_000_000.0, requiredStage = CafeStage.REGIONAL_BRAND),
    SEASONAL_DRINK("menu_seasonal_drink", basePrice = 7.00, baseIngredientCost = 1.50, baseBrewSeconds = 12.0, unlockCost = 0.0, requiredStage = CafeStage.REGIONAL_BRAND);

    val baseMargin: Double get() = basePrice - baseIngredientCost
}
