package com.coffeeshoptycoon.game.domain.model

/**
 * A personality trait rolled when an employee is hired. Modifiers are multiplicative
 * against the base stat (1.0 == no change).
 */
enum class EmployeeTrait(
    val displayKey: String,
    val speedModifier: Double = 1.0,
    val tipModifier: Double = 1.0,
    val reviewModifier: Double = 1.0,
    val hireCostModifier: Double = 1.0,
    val moraleRegenModifier: Double = 1.0
) {
    NONE("trait_none"),
    FAST_HANDS("trait_fast_hands", speedModifier = 1.10),
    PEOPLE_PERSON("trait_people_person", tipModifier = 1.15),
    PERFECTIONIST("trait_perfectionist", speedModifier = 0.95, reviewModifier = 1.10),
    HARD_WORKER("trait_hard_worker", speedModifier = 1.05, moraleRegenModifier = 1.05),
    CLUMSY("trait_clumsy", speedModifier = 0.90, hireCostModifier = 0.85),
    NIGHT_OWL("trait_night_owl", speedModifier = 1.08, moraleRegenModifier = 0.95)
}
