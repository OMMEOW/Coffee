package com.coffeeshoptycoon.game.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffeeshoptycoon.game.domain.model.StoreUpgradeType

@Entity(tableName = "store_upgrades")
data class StoreUpgradeEntity(
    @PrimaryKey val type: StoreUpgradeType,
    val level: Int
)
