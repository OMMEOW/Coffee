package com.coffeeshoptycoon.game.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coffeeshoptycoon.game.domain.model.EmployeeRole
import com.coffeeshoptycoon.game.domain.model.EmployeeTrait

@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val role: EmployeeRole,
    val trait: EmployeeTrait,
    val level: Int,
    val experience: Double,
    val morale: Double,
    val assignedMachineId: String?,
    val timesTrained: Int,
    val hiredAtTimestamp: Long
)
