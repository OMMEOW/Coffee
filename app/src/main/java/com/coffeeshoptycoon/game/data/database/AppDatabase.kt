package com.coffeeshoptycoon.game.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coffeeshoptycoon.game.data.database.dao.EmployeeDao
import com.coffeeshoptycoon.game.data.database.dao.GameStateDao
import com.coffeeshoptycoon.game.data.database.dao.MachineDao
import com.coffeeshoptycoon.game.data.database.dao.StatisticsDao
import com.coffeeshoptycoon.game.data.database.dao.StoreUpgradeDao
import com.coffeeshoptycoon.game.data.database.entity.EmployeeEntity
import com.coffeeshoptycoon.game.data.database.entity.GameStateEntity
import com.coffeeshoptycoon.game.data.database.entity.MachineEntity
import com.coffeeshoptycoon.game.data.database.entity.StatisticsEntity
import com.coffeeshoptycoon.game.data.database.entity.StoreUpgradeEntity

/**
 * The player's single local save file (docs/GDD.md §15). Schema changes must be additive
 * Room [androidx.room.migration.Migration]s registered in [com.coffeeshoptycoon.game.di.DatabaseModule] —
 * never a destructive fallback — so a version bump never silently wipes progress.
 */
@Database(
    entities = [
        GameStateEntity::class,
        EmployeeEntity::class,
        MachineEntity::class,
        StoreUpgradeEntity::class,
        StatisticsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameStateDao(): GameStateDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun machineDao(): MachineDao
    abstract fun storeUpgradeDao(): StoreUpgradeDao
    abstract fun statisticsDao(): StatisticsDao

    companion object {
        const val DATABASE_NAME = "coffee_tycoon.db"
    }
}
