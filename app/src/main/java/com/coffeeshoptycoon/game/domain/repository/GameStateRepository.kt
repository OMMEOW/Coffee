package com.coffeeshoptycoon.game.domain.repository

import com.coffeeshoptycoon.game.domain.model.GameState
import kotlinx.coroutines.flow.StateFlow

/**
 * The single source of truth for the player's save (docs/GDD.md §15). All game-logic
 * mutations — production, purchases, prestige, achievements — go through
 * [updateGameState] so persistence and in-memory state can never drift apart.
 */
interface GameStateRepository {

    /** Hot, always-current game state. Emits the freshly-initialized default state on first ever launch. */
    val gameState: StateFlow<GameState>

    suspend fun getCurrent(): GameState

    /** Applies [transform] to the current state and persists the result atomically. */
    suspend fun updateGameState(transform: (GameState) -> GameState)

    /** Wipes the current run's save data. Used by prestige (which then seeds [newState]) and by debug/new-game flows. */
    suspend fun resetTo(newState: GameState)
}
