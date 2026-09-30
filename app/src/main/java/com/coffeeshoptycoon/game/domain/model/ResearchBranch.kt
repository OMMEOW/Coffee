package com.coffeeshoptycoon.game.domain.model

/** The 8 research tree branches from docs/GDD.md §7. */
enum class ResearchBranch(val displayKey: String) {
    AUTOMATION("research_branch_automation"),
    MARKETING("research_branch_marketing"),
    RECIPES("research_branch_recipes"),
    EFFICIENCY("research_branch_efficiency"),
    CUSTOMER_HAPPINESS("research_branch_customer_happiness"),
    FURNITURE("research_branch_furniture"),
    AI_ORDERING("research_branch_ai_ordering"),
    DELIVERY("research_branch_delivery")
}
