package com.coffeeshoptycoon.game.data.database.mapper

import com.coffeeshoptycoon.game.data.database.entity.EmployeeEntity
import com.coffeeshoptycoon.game.data.database.entity.GameStateEntity
import com.coffeeshoptycoon.game.data.database.entity.MachineEntity
import com.coffeeshoptycoon.game.data.database.entity.StatisticsEntity
import com.coffeeshoptycoon.game.data.database.entity.StoreUpgradeEntity
import com.coffeeshoptycoon.game.domain.model.Employee
import com.coffeeshoptycoon.game.domain.model.GameState
import com.coffeeshoptycoon.game.domain.model.GameStatistics
import com.coffeeshoptycoon.game.domain.model.Machine
import com.coffeeshoptycoon.game.domain.model.StoreUpgrade

/**
 * [GameState] is a single aggregate but is stored across several Room tables (see
 * data/database/entity). These pure functions are the only place that shape is
 * translated in either direction.
 */

fun EmployeeEntity.toDomain(): Employee = Employee(
    id = id, name = name, role = role, trait = trait, level = level,
    experience = experience, morale = morale, assignedMachineId = assignedMachineId,
    timesTrained = timesTrained, hiredAtTimestamp = hiredAtTimestamp
)

fun Employee.toEntity(): EmployeeEntity = EmployeeEntity(
    id = id, name = name, role = role, trait = trait, level = level,
    experience = experience, morale = morale, assignedMachineId = assignedMachineId,
    timesTrained = timesTrained, hiredAtTimestamp = hiredAtTimestamp
)

fun MachineEntity.toDomain(): Machine = Machine(id = id, type = type, level = level)

fun Machine.toEntity(): MachineEntity = MachineEntity(id = id, type = type, level = level)

fun StoreUpgradeEntity.toDomain(): StoreUpgrade = StoreUpgrade(type = type, level = level)

fun StoreUpgrade.toEntity(): StoreUpgradeEntity = StoreUpgradeEntity(type = type, level = level)

fun StatisticsEntity.toDomain(): GameStatistics = GameStatistics(
    totalCashEarnedAllTime = totalCashEarnedAllTime,
    totalCustomersServedAllTime = totalCustomersServedAllTime,
    drinksSoldByType = drinksSoldByType,
    totalPlaytimeSeconds = totalPlaytimeSeconds,
    totalPrestiges = totalPrestiges,
    currentPerfectReviewStreak = currentPerfectReviewStreak,
    longestPerfectReviewStreak = longestPerfectReviewStreak,
    eventsParticipated = eventsParticipated
)

fun GameStatistics.toEntity(): StatisticsEntity = StatisticsEntity(
    totalCashEarnedAllTime = totalCashEarnedAllTime,
    totalCustomersServedAllTime = totalCustomersServedAllTime,
    drinksSoldByType = drinksSoldByType,
    totalPlaytimeSeconds = totalPlaytimeSeconds,
    totalPrestiges = totalPrestiges,
    currentPerfectReviewStreak = currentPerfectReviewStreak,
    longestPerfectReviewStreak = longestPerfectReviewStreak,
    eventsParticipated = eventsParticipated
)

fun GameStateEntity.toDomain(
    employees: List<Employee>,
    machines: List<Machine>,
    storeUpgrades: List<StoreUpgrade>,
    statistics: GameStatistics
): GameState = GameState(
    cash = cash,
    roastedBeans = roastedBeans,
    loyaltyPoints = loyaltyPoints,
    eventTokens = eventTokens,
    lifetimeCashEarnedThisRun = lifetimeCashEarnedThisRun,
    reputation = reputation,
    unlockedMenuItems = unlockedMenuItems,
    employees = employees,
    machines = machines,
    storeUpgrades = storeUpgrades,
    inventory = inventory,
    completedResearchIds = completedResearchIds,
    prestigeUpgradeLevels = prestigeUpgradeLevels,
    unlockedAchievementIds = unlockedAchievementIds,
    activeSeasonalEvent = activeSeasonalEvent,
    activeRandomEvents = activeRandomEvents,
    dailyRewardStreak = dailyRewardStreak,
    dailyRewardCumulativeDays = dailyRewardCumulativeDays,
    lastDailyRewardClaimEpochDay = lastDailyRewardClaimEpochDay,
    prestigeCount = prestigeCount,
    lastActiveTimestamp = lastActiveTimestamp,
    statistics = statistics
)

fun GameState.toEntity(): GameStateEntity = GameStateEntity(
    cash = cash,
    roastedBeans = roastedBeans,
    loyaltyPoints = loyaltyPoints,
    eventTokens = eventTokens,
    lifetimeCashEarnedThisRun = lifetimeCashEarnedThisRun,
    reputation = reputation,
    unlockedMenuItems = unlockedMenuItems,
    inventory = inventory,
    completedResearchIds = completedResearchIds,
    prestigeUpgradeLevels = prestigeUpgradeLevels,
    unlockedAchievementIds = unlockedAchievementIds,
    activeSeasonalEvent = activeSeasonalEvent,
    activeRandomEvents = activeRandomEvents,
    dailyRewardStreak = dailyRewardStreak,
    dailyRewardCumulativeDays = dailyRewardCumulativeDays,
    lastDailyRewardClaimEpochDay = lastDailyRewardClaimEpochDay,
    prestigeCount = prestigeCount,
    lastActiveTimestamp = lastActiveTimestamp
)
