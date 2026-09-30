package com.coffeeshoptycoon.game.data.repository

import androidx.room.withTransaction
import com.coffeeshoptycoon.game.core.common.ApplicationScope
import com.coffeeshoptycoon.game.data.database.AppDatabase
import com.coffeeshoptycoon.game.data.database.dao.EmployeeDao
import com.coffeeshoptycoon.game.data.database.dao.GameStateDao
import com.coffeeshoptycoon.game.data.database.dao.MachineDao
import com.coffeeshoptycoon.game.data.database.dao.StatisticsDao
import com.coffeeshoptycoon.game.data.database.dao.StoreUpgradeDao
import com.coffeeshoptycoon.game.data.database.mapper.toDomain
import com.coffeeshoptycoon.game.data.database.mapper.toEntity
import com.coffeeshoptycoon.game.domain.model.GameState
import com.coffeeshoptycoon.game.domain.model.GameStatistics
import com.coffeeshoptycoon.game.domain.repository.GameStateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameStateRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val gameStateDao: GameStateDao,
    private val employeeDao: EmployeeDao,
    private val machineDao: MachineDao,
    private val storeUpgradeDao: StoreUpgradeDao,
    private val statisticsDao: StatisticsDao,
    @ApplicationScope private val applicationScope: CoroutineScope
) : GameStateRepository {

    override val gameState: StateFlow<GameState> = combine(
        gameStateDao.observe(),
        employeeDao.observeAll(),
        machineDao.observeAll(),
        storeUpgradeDao.observeAll(),
        statisticsDao.observe()
    ) { gameStateEntity, employees, machines, storeUpgrades, statisticsEntity ->
        if (gameStateEntity == null) {
            GameState()
        } else {
            gameStateEntity.toDomain(
                employees = employees.map { it.toDomain() },
                machines = machines.map { it.toDomain() },
                storeUpgrades = storeUpgrades.map { it.toDomain() },
                statistics = statisticsEntity?.toDomain() ?: GameStatistics()
            )
        }
    }.stateIn(applicationScope, SharingStarted.Eagerly, GameState())

    init {
        applicationScope.launch { ensureSeeded() }
    }

    private suspend fun ensureSeeded() {
        if (gameStateDao.get() == null) {
            persist(GameState())
        }
    }

    override suspend fun getCurrent(): GameState = gameState.value

    override suspend fun updateGameState(transform: (GameState) -> GameState) {
        persist(transform(getCurrent()))
    }

    override suspend fun resetTo(newState: GameState) {
        persist(newState)
    }

    private suspend fun persist(state: GameState) {
        database.withTransaction {
            gameStateDao.upsert(state.toEntity())
            employeeDao.deleteAll()
            employeeDao.upsertAll(state.employees.map { it.toEntity() })
            machineDao.deleteAll()
            machineDao.upsertAll(state.machines.map { it.toEntity() })
            storeUpgradeDao.deleteAll()
            storeUpgradeDao.upsertAll(state.storeUpgrades.map { it.toEntity() })
            statisticsDao.upsert(state.statistics.toEntity())
        }
    }
}
