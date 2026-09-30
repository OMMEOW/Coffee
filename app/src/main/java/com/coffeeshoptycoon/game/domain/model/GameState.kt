package com.coffeeshoptycoon.game.domain.model

/**
 * The full aggregate save state for the current prestige run plus everything that
 * survives prestige (Roasted Beans, Loyalty Points, achievements, statistics, prestige
 * upgrades). This is the in-memory shape the engine and ViewModels operate on; Room
 * entities in data/database mirror it for persistence (see docs/GDD.md §15).
 */
data class GameState(
    val cash: Double = 500.0,
    val roastedBeans: Double = 0.0,
    val loyaltyPoints: Double = 0.0,
    val eventTokens: Double = 0.0,
    val lifetimeCashEarnedThisRun: Double = 0.0,
    val reputation: Double = 3.0,
    val unlockedMenuItems: Set<MenuItemType> = setOf(MenuItemType.ESPRESSO),
    val employees: List<Employee> = listOf(
        Employee(id = "starter_employee", name = "Alex", role = EmployeeRole.BARISTA, trait = EmployeeTrait.NONE)
    ),
    val machines: List<Machine> = listOf(
        Machine(id = "starter_coffee_machine", type = MachineType.COFFEE_MACHINE, level = 1)
    ),
    val storeUpgrades: List<StoreUpgrade> = emptyList(),
    val inventory: Map<IngredientType, Int> = IngredientType.entries.associateWith { 100 },
    val completedResearchIds: Set<String> = emptySet(),
    val prestigeUpgradeLevels: Map<String, Int> = emptyMap(),
    val unlockedAchievementIds: Set<String> = emptySet(),
    val activeSeasonalEvent: ActiveSeasonalEvent? = null,
    val activeRandomEvents: List<ActiveRandomEvent> = emptyList(),
    val dailyRewardStreak: Int = 0,
    val dailyRewardCumulativeDays: Int = 0,
    val lastDailyRewardClaimEpochDay: Long = -1,
    val prestigeCount: Int = 0,
    val lastActiveTimestamp: Long = System.currentTimeMillis(),
    val statistics: GameStatistics = GameStatistics()
) {
    val currentStage: CafeStage get() = CafeStage.forLifetimeRevenue(lifetimeCashEarnedThisRun)

    val storeCapacity: Int
        get() = BASE_CUSTOMER_CAPACITY + storeUpgrades.sumOf { it.capacityBonus }

    companion object {
        const val BASE_CUSTOMER_CAPACITY = 4
        const val MAX_OFFLINE_HOURS = 24.0
    }
}
