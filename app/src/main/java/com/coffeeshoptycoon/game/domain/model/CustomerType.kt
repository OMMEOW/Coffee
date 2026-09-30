package com.coffeeshoptycoon.game.domain.model

/**
 * The 7 customer personalities from docs/GDD.md §"Customers". [spawnWeight] is relative
 * (not a probability) and is combined with reputation gating in the engine's spawner;
 * [reviewWeight] scales how much a single served/lost customer of this type moves the
 * rolling reputation average.
 */
enum class CustomerType(
    val displayKey: String,
    val spawnWeight: Double,
    val basePatienceSeconds: Double,
    val baseBudget: Double,
    val tipModifier: Double,
    val reviewWeight: Double,
    val minRequiredStage: CafeStage,
    val minReputationRequired: Double
) {
    MORNING_RUSH("customer_morning_rush", spawnWeight = 30.0, basePatienceSeconds = 20.0, baseBudget = 8.0, tipModifier = 1.0, reviewWeight = 1.0, minRequiredStage = CafeStage.STALL, minReputationRequired = 0.0),
    STUDENT("customer_student", spawnWeight = 20.0, basePatienceSeconds = 35.0, baseBudget = 5.0, tipModifier = 0.8, reviewWeight = 0.8, minRequiredStage = CafeStage.STALL, minReputationRequired = 0.0),
    OFFICE_WORKER("customer_office_worker", spawnWeight = 20.0, basePatienceSeconds = 25.0, baseBudget = 10.0, tipModifier = 1.1, reviewWeight = 1.0, minRequiredStage = CafeStage.CORNER_CAFE, minReputationRequired = 0.0),
    TOURIST("customer_tourist", spawnWeight = 12.0, basePatienceSeconds = 30.0, baseBudget = 14.0, tipModifier = 1.2, reviewWeight = 0.5, minRequiredStage = CafeStage.NEIGHBORHOOD_SHOP, minReputationRequired = 1.5),
    COFFEE_LOVER("customer_coffee_lover", spawnWeight = 10.0, basePatienceSeconds = 40.0, baseBudget = 12.0, tipModifier = 1.3, reviewWeight = 1.2, minRequiredStage = CafeStage.POPULAR_CAFE, minReputationRequired = 2.0),
    VIP("customer_vip", spawnWeight = 5.0, basePatienceSeconds = 15.0, baseBudget = 30.0, tipModifier = 2.0, reviewWeight = 2.0, minRequiredStage = CafeStage.LOCAL_CHAIN, minReputationRequired = 3.0),
    INFLUENCER("customer_influencer", spawnWeight = 3.0, basePatienceSeconds = 20.0, baseBudget = 20.0, tipModifier = 1.5, reviewWeight = 4.0, minRequiredStage = CafeStage.CITY_FAVORITE, minReputationRequired = 3.5)
}
