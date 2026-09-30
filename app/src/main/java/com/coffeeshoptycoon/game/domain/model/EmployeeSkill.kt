package com.coffeeshoptycoon.game.domain.model

/**
 * Passive perks automatically unlocked as an employee levels up (see
 * [Employee.unlockedSkills]). Unlike traits, skills are earned, not rolled.
 */
enum class EmployeeSkill(val displayKey: String, val unlockLevel: Int, val speedModifier: Double = 1.0, val tipModifier: Double = 1.0, val reviewModifier: Double = 1.0) {
    QUICK_LEARNER("skill_quick_learner", unlockLevel = 2, speedModifier = 1.05),
    STEADY_HANDS("skill_steady_hands", unlockLevel = 5, reviewModifier = 1.05),
    CROWD_PLEASER("skill_crowd_pleaser", unlockLevel = 10, tipModifier = 1.10),
    MULTITASKER("skill_multitasker", unlockLevel = 15, speedModifier = 1.10),
    CAFE_LEGEND("skill_cafe_legend", unlockLevel = 20, speedModifier = 1.10, tipModifier = 1.10, reviewModifier = 1.10);

    companion object {
        fun unlockedAtOrBelow(level: Int): Set<EmployeeSkill> =
            entries.filter { level >= it.unlockLevel }.toSet()
    }
}
