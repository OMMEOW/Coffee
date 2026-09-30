package com.coffeeshoptycoon.game.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coffeeshoptycoon.game.data.database.entity.GameStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameStateDao {

    @Query("SELECT * FROM game_state WHERE id = 0")
    fun observe(): Flow<GameStateEntity?>

    @Query("SELECT * FROM game_state WHERE id = 0")
    suspend fun get(): GameStateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: GameStateEntity)

    @Query("DELETE FROM game_state")
    suspend fun clear()
}
