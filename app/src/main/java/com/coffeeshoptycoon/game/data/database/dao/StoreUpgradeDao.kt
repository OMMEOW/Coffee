package com.coffeeshoptycoon.game.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coffeeshoptycoon.game.data.database.entity.StoreUpgradeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreUpgradeDao {

    @Query("SELECT * FROM store_upgrades")
    fun observeAll(): Flow<List<StoreUpgradeEntity>>

    @Query("SELECT * FROM store_upgrades")
    suspend fun getAll(): List<StoreUpgradeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: StoreUpgradeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(entities: List<StoreUpgradeEntity>)

    @Query("DELETE FROM store_upgrades")
    suspend fun deleteAll()
}
