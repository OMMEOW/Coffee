package com.coffeeshoptycoon.game.domain.model

/**
 * The 8 staff roles from docs/GDD.md §6. [effectPerLevel] is the fractional bonus
 * (e.g. 0.05 == +5%) that role contributes per level toward its primary effect; the
 * concrete formula each role feeds is documented per-entry and applied by the engine's
 * ProductionCalculator / CustomerSpawner / EventScheduler.
 */
enum class EmployeeRole(
    val displayKey: String,
    val baseHireCost: Double,
    val baseSalaryPerDay: Double,
    val requiredStage: CafeStage,
    val effectPerLevel: Double
) {
    /** Speeds up order intake, shortening the time a customer waits before brewing starts. */
    CASHIER("role_cashier", baseHireCost = 200.0, baseSalaryPerDay = 20.0, requiredStage = CafeStage.STALL, effectPerLevel = 0.05),

    /** Speeds up brewing/preparation at an assigned machine. The core production role. */
    BARISTA("role_barista", baseHireCost = 300.0, baseSalaryPerDay = 25.0, requiredStage = CafeStage.STALL, effectPerLevel = 0.08),

    /** Raises ambient customer mood, improving tips and review scores store-wide. */
    CLEANER("role_cleaner", baseHireCost = 250.0, baseSalaryPerDay = 18.0, requiredStage = CafeStage.CORNER_CAFE, effectPerLevel = 0.03),

    /** Boosts the productivity of every other staff member store-wide. */
    MANAGER("role_manager", baseHireCost = 2_000.0, baseSalaryPerDay = 60.0, requiredStage = CafeStage.POPULAR_CAFE, effectPerLevel = 0.02),

    /** Raises customer arrival rate. */
    MARKETING("role_marketing", baseHireCost = 1_500.0, baseSalaryPerDay = 45.0, requiredStage = CafeStage.LOCAL_CHAIN, effectPerLevel = 0.04),

    /** Raises morale regeneration rate and lowers training cost for all staff. */
    HR("role_hr", baseHireCost = 1_200.0, baseSalaryPerDay = 40.0, requiredStage = CafeStage.LOCAL_CHAIN, effectPerLevel = 0.05),

    /** Raises the experience gain rate of every other staff member. */
    TRAINER("role_trainer", baseHireCost = 1_800.0, baseSalaryPerDay = 50.0, requiredStage = CafeStage.CITY_FAVORITE, effectPerLevel = 0.06),

    /** Lowers the chance of negative random events (theft, failed inspections, breakdowns). */
    SECURITY("role_security", baseHireCost = 2_500.0, baseSalaryPerDay = 55.0, requiredStage = CafeStage.REGIONAL_BRAND, effectPerLevel = 0.05)
}
