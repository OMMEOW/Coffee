package com.coffeeshoptycoon.game.domain.model

/**
 * A single quantified bonus granted by a research node or a prestige upgrade. Kept as
 * plain data (not behavior) so the engine can fold a list of these into multipliers
 * without either side depending on the other.
 */
sealed class GameEffect {
    data class ProductionSpeedMultiplier(val amount: Double) : GameEffect()
    data class IncomeMultiplier(val amount: Double) : GameEffect()
    data class CustomerArrivalMultiplier(val amount: Double) : GameEffect()
    data class PatienceMultiplier(val amount: Double) : GameEffect()
    data class ReputationGainMultiplier(val amount: Double) : GameEffect()
    data class EmployeeSalaryDiscount(val amount: Double) : GameEffect()
    data class IngredientCostDiscount(val amount: Double) : GameEffect()
    data class MenuUnlockCostDiscount(val amount: Double) : GameEffect()
    data class OfflineEarningsCapBonusHours(val hours: Double) : GameEffect()
    data class OfflineEarningsRateMultiplier(val amount: Double) : GameEffect()
    data class StartingCashBonus(val amount: Double) : GameEffect()
    data class AutoUnlockMenuItem(val menuItem: MenuItemType) : GameEffect()
    data class NegativeEventChanceMultiplier(val amount: Double) : GameEffect()
    data class DeliveryIncomeBonus(val amount: Double) : GameEffect()
}
