package com.coffeeshoptycoon.game.di

import android.content.Context
import androidx.room.Room
import com.coffeeshoptycoon.game.data.database.AppDatabase
import com.coffeeshoptycoon.game.data.database.dao.EmployeeDao
import com.coffeeshoptycoon.game.data.database.dao.GameStateDao
import com.coffeeshoptycoon.game.data.database.dao.MachineDao
import com.coffeeshoptycoon.game.data.database.dao.StatisticsDao
import com.coffeeshoptycoon.game.data.database.dao.StoreUpgradeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Register additive Room [androidx.room.migration.Migration]s here as they're needed —
 * never `fallbackToDestructiveMigration`, per the save-system guarantee in docs/GDD.md §15.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()

    @Provides
    fun providesGameStateDao(database: AppDatabase): GameStateDao = database.gameStateDao()

    @Provides
    fun providesEmployeeDao(database: AppDatabase): EmployeeDao = database.employeeDao()

    @Provides
    fun providesMachineDao(database: AppDatabase): MachineDao = database.machineDao()

    @Provides
    fun providesStoreUpgradeDao(database: AppDatabase): StoreUpgradeDao = database.storeUpgradeDao()

    @Provides
    fun providesStatisticsDao(database: AppDatabase): StatisticsDao = database.statisticsDao()
}
