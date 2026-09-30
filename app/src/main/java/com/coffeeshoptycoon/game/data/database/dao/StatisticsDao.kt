package com.coffeeshoptycoon.game.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coffeeshoptycoon.game.data.database.entity.StatisticsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticsDao {

    @Query("SELECT * FROM statistics WHERE id = 0")
    fun observe(): Flow<StatisticsEntity?>

    @Query("SELECT * FROM statistics WHERE id = 0")
    suspend fun get(): StatisticsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: StatisticsEntity)

    @Query("DELETE FROM statistics")
    suspend fun clear()
}
