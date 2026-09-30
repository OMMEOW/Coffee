package com.coffeeshoptycoon.game.domain.model

/** The lifetime statistic an [Achievement] tracks progress against. */
sealed class AchievementMetric {
    data object LifetimeCashEarned : AchievementMetric()
    data object CustomersServed : AchievementMetric()
    data object DrinksSoldTotal : AchievementMetric()
    data class DrinksSoldByType(val menuItem: MenuItemType) : AchievementMetric()
    data object EmployeesHired : AchievementMetric()
    data object HighestEmployeeLevel : AchievementMetric()
    data object MachineUpgradesTotal : AchievementMetric()
    data object ResearchCompleted : AchievementMetric()
    data object PrestigeCount : AchievementMetric()
    data object StoreUpgradesOwned : AchievementMetric()
    data object PerfectReviewStreak : AchievementMetric()
    data object EventsParticipated : AchievementMetric()
    data object PlaytimeSeconds : AchievementMetric()
    data object VipStreakServed : AchievementMetric()
}
