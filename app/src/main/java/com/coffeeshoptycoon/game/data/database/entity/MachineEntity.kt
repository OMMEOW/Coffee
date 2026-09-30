package com.coffeeshoptycoon.game.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffeeshoptycoon.game.domain.model.MachineType

@Entity(tableName = "machines")
data class MachineEntity(
    @PrimaryKey val id: String,
    val type: MachineType,
    val level: Int
)
