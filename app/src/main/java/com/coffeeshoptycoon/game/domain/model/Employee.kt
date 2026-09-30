package com.coffeeshoptycoon.game.domain.model

import kotlin.math.pow

/**
 * A hired staff member. Levels, morale, and skills are mutable over the run; [trait]
 * is rolled once at hire time and never changes.
 */
data class Employee(
    val id: String,
    val name: String,
    val role: EmployeeRole,
    val trait: EmployeeTrait,
    val level: Int = 1,
    val experience: Double = 0.0,
    val morale: Double = 0.8,
    val assignedMachineId: String? = null,
    val timesTrained: Int = 0,
    val hiredAtTimestamp: Long = System.currentTimeMillis()
) {
    val unlockedSkills: Set<EmployeeSkill> get() = EmployeeSkill.unlockedAtOrBelow(level)

    /** Fraction of top speed lost to low morale; 0 morale halves output, full morale is neutral. */
    private val moraleFactor: Double get() = 0.5 + (morale.coerceIn(0.0, 1.0) * 0.5)

    val speedMultiplier: Double
        get() {
            val roleBonus = 1.0 + role.effectPerLevel * (level - 1)
            val skillBonus = unlockedSkills.fold(1.0) { acc, skill -> acc * skill.speedModifier }
            return roleBonus * skillBonus * trait.speedModifier * moraleFactor
        }

    val tipMultiplier: Double
        get() {
            val skillBonus = unlockedSkills.fold(1.0) { acc, skill -> acc * skill.tipModifier }
            return skillBonus * trait.tipModifier
        }

    val reviewMultiplier: Double
        get() {
            val skillBonus = unlockedSkills.fold(1.0) { acc, skill -> acc * skill.reviewModifier }
            return skillBonus * trait.reviewModifier
        }

    val salaryPerDay: Double
        get() = role.baseSalaryPerDay * (1.0 + 0.08 * (level - 1))

    val experienceToNextLevel: Double
        get() = experienceRequiredFor(level)

    companion object {
        const val MAX_LEVEL = 30

        fun experienceRequiredFor(level: Int): Double = 100.0 * 1.15.pow(level - 1)

        fun hireCostFor(role: EmployeeRole, trait: EmployeeTrait, existingCountOfRole: Int): Double =
            role.baseHireCost * trait.hireCostModifier * 1.20.pow(existingCountOfRole)
    }
}
