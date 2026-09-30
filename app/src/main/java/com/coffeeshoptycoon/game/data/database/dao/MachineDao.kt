package com.coffeeshoptycoon.game.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coffeeshoptycoon.game.data.database.entity.MachineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MachineDao {

    @Query("SELECT * FROM machines")
    fun observeAll(): Flow<List<MachineEntity>>

    @Query("SELECT * FROM machines")
    suspend fun getAll(): List<MachineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: MachineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(entities: List<MachineEntity>)

    @Delete
    suspend fun delete(entity: MachineEntity)

    @Query("DELETE FROM machines")
    suspend fun deleteAll()
}
