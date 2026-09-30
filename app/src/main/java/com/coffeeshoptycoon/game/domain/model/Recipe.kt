package com.coffeeshoptycoon.game.domain.model

/** Ingredient units consumed each time [menuItem] is brewed/prepared once. */
data class Recipe(
    val menuItem: MenuItemType,
    val ingredients: Map<IngredientType, Int>
)

/** Static recipe book — the only place ingredient requirements are defined. */
object RecipeCatalog {

    private val recipes: Map<MenuItemType, Recipe> = listOf(
        Recipe(MenuItemType.ESPRESSO, mapOf(IngredientType.COFFEE_BEANS to 1)),
        Recipe(MenuItemType.LATTE, mapOf(IngredientType.COFFEE_BEANS to 1, IngredientType.MILK to 2)),
        Recipe(MenuItemType.MOCHA, mapOf(IngredientType.COFFEE_BEANS to 1, IngredientType.MILK to 2, IngredientType.CHOCOLATE to 1)),
        Recipe(MenuItemType.AMERICANO, mapOf(IngredientType.COFFEE_BEANS to 2)),
        Recipe(MenuItemType.COLD_BREW, mapOf(IngredientType.COFFEE_BEANS to 2, IngredientType.ICE to 2)),
        Recipe(MenuItemType.TEA, mapOf(IngredientType.TEA_LEAVES to 1)),
        Recipe(MenuItemType.SMOOTHIE, mapOf(IngredientType.FRUIT to 2, IngredientType.ICE to 1, IngredientType.MILK to 1)),
        Recipe(MenuItemType.CAKE, mapOf(IngredientType.FLOUR to 2, IngredientType.CHOCOLATE to 1, IngredientType.SUGAR to 1)),
        Recipe(MenuItemType.COOKIE, mapOf(IngredientType.FLOUR to 1, IngredientType.SUGAR to 1)),
        Recipe(MenuItemType.DONUT, mapOf(IngredientType.FLOUR to 1, IngredientType.SUGAR to 1, IngredientType.CHOCOLATE to 1)),
        Recipe(MenuItemType.SANDWICH, mapOf(IngredientType.BREAD to 2, IngredientType.FRUIT to 1)),
        Recipe(MenuItemType.PREMIUM_COFFEE, mapOf(IngredientType.COFFEE_BEANS to 3, IngredientType.MILK to 1)),
        Recipe(MenuItemType.ICED_LATTE, mapOf(IngredientType.COFFEE_BEANS to 1, IngredientType.MILK to 2, IngredientType.ICE to 2)),
        Recipe(MenuItemType.SEASONAL_DRINK, mapOf(IngredientType.COFFEE_BEANS to 1, IngredientType.MILK to 1, IngredientType.SUGAR to 1))
    ).associateBy { it.menuItem }

    fun forMenuItem(menuItem: MenuItemType): Recipe = recipes.getValue(menuItem)

    fun all(): Collection<Recipe> = recipes.values
}
