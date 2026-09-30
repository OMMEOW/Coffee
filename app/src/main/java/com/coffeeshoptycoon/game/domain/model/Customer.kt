package com.coffeeshoptycoon.game.domain.model

import kotlin.math.max
import kotlin.math.min

/** A single customer currently queued or being served at the café. */
data class Customer(
    val id: String,
    val type: CustomerType,
    val favoriteDrink: MenuItemType,
    val budget: Double,
    val patienceRemainingSeconds: Double,
    val mood: Double = 1.0,
    val arrivalTimestamp: Long = System.currentTimeMillis()
) {
    val hasLeft: Boolean get() = patienceRemainingSeconds <= 0.0

    /**
     * Review score in stars (0..5) for being served [itemServed] after [waitSeconds] of
     * queueing. Matching the favorite drink and a short wait both raise the score.
     */
    fun reviewScoreFor(itemServed: MenuItemType, waitSeconds: Double): Double {
        val matchBonus = if (itemServed == favoriteDrink) 1.0 else 0.0
        val patienceRatio = (patienceRemainingSeconds + waitSeconds).let { total ->
            if (total <= 0.0) 0.0 else 1.0 - (waitSeconds / total)
        }
        val waitScore = 2.5 * patienceRatio.coerceIn(0.0, 1.0)
        val moodScore = 1.5 * mood.coerceIn(0.0, 1.0)
        return min(5.0, max(0.0, waitScore + moodScore + matchBonus))
    }

    /** Tip in dollars for being served [itemServed], derived from budget, type, and review. */
    fun tipFor(itemServed: MenuItemType, itemPrice: Double, waitSeconds: Double): Double {
        val review = reviewScoreFor(itemServed, waitSeconds)
        val tipRate = (review / 5.0) * 0.25 * type.tipModifier
        return (itemPrice * tipRate).coerceAtMost(budget * 0.5)
    }
}
