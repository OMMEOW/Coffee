package com.coffeeshoptycoon.game.domain.model

/**
 * A single node in the research tree (docs/GDD.md §7). Effects reset on prestige along
 * with everything else in [GameState.completedResearchIds]; [prerequisiteId] must be
 * completed, and [requiredStage] reached, before this node can be purchased.
 */
data class ResearchNode(
    val id: String,
    val branch: ResearchBranch,
    val tier: Int,
    val displayKey: String,
    val descriptionKey: String,
    val cashCost: Double,
    val loyaltyCost: Double = 0.0,
    val requiredStage: CafeStage,
    val prerequisiteId: String?,
    val effects: List<GameEffect>
)
