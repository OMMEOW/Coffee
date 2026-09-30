package com.coffeeshoptycoon.game.domain.model

/**
 * Stockable ingredients consumed by [Recipe]s. Running out disables the affected
 * menu items until a restock purchase is made (or a Coffee Bean Shortage event resolves).
 */
enum class IngredientType(val displayKey: String, val restockUnitCost: Double, val unitsPerRestock: Int) {
    COFFEE_BEANS("ingredient_coffee_beans", 0.05, 200),
    MILK("ingredient_milk", 0.03, 200),
    SUGAR("ingredient_sugar", 0.01, 200),
    ICE("ingredient_ice", 0.01, 300),
    FLOUR("ingredient_flour", 0.02, 150),
    CHOCOLATE("ingredient_chocolate", 0.06, 150),
    FRUIT("ingredient_fruit", 0.08, 150),
    BREAD("ingredient_bread", 0.10, 100),
    TEA_LEAVES("ingredient_tea_leaves", 0.04, 200)
}
