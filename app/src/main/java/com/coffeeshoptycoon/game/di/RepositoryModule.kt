package com.coffeeshoptycoon.game.di

import com.coffeeshoptycoon.game.data.repository.GameStateRepositoryImpl
import com.coffeeshoptycoon.game.data.repository.SettingsRepositoryImpl
import com.coffeeshoptycoon.game.domain.repository.GameStateRepository
import com.coffeeshoptycoon.game.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsGameStateRepository(impl: GameStateRepositoryImpl): GameStateRepository

    @Binds
    @Singleton
    abstract fun bindsSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}
