package com.coffeeshoptycoon.game.data.database

import androidx.room.TypeConverter
import com.coffeeshoptycoon.game.domain.model.ActiveRandomEvent
import com.coffeeshoptycoon.game.domain.model.ActiveSeasonalEvent
import com.coffeeshoptycoon.game.domain.model.EmployeeRole
import com.coffeeshoptycoon.game.domain.model.EmployeeTrait
import com.coffeeshoptycoon.game.domain.model.IngredientType
import com.coffeeshoptycoon.game.domain.model.MachineType
import com.coffeeshoptycoon.game.domain.model.MenuItemType
import com.coffeeshoptycoon.game.domain.model.RandomEventType
import com.coffeeshoptycoon.game.domain.model.SeasonalEventType
import com.coffeeshoptycoon.game.domain.model.StoreUpgradeType

/**
 * Every collection/enum field persisted directly on a [com.coffeeshoptycoon.game.data.database.entity]
 * class is encoded here as a delimited string rather than JSON: all shapes involved are
 * small, fixed, and flat (enum sets, enum->Int maps), so a dependency on a JSON library
 * would add more surface area than it would save.
 */
class Converters {

    // --- Simple enums used directly as entity columns ---

    @TypeConverter
    fun fromEmployeeRole(value: EmployeeRole): String = value.name

    @TypeConverter
    fun toEmployeeRole(value: String): EmployeeRole = EmployeeRole.valueOf(value)

    @TypeConverter
    fun fromEmployeeTrait(value: EmployeeTrait): String = value.name

    @TypeConverter
    fun toEmployeeTrait(value: String): EmployeeTrait = EmployeeTrait.valueOf(value)

    @TypeConverter
    fun fromMachineType(value: MachineType): String = value.name

    @TypeConverter
    fun toMachineType(value: String): MachineType = MachineType.valueOf(value)

    @TypeConverter
    fun fromStoreUpgradeType(value: StoreUpgradeType): String = value.name

    @TypeConverter
    fun toStoreUpgradeType(value: String): StoreUpgradeType = StoreUpgradeType.valueOf(value)

    // --- Sets / maps on GameStateEntity ---

    @TypeConverter
    fun fromMenuItemSet(value: Set<MenuItemType>): String = value.joinToString(",") { it.name }

    @TypeConverter
    fun toMenuItemSet(value: String): Set<MenuItemType> =
        if (value.isBlank()) emptySet() else value.split(",").map { MenuItemType.valueOf(it) }.toSet()

    @TypeConverter
    fun fromStringSet(value: Set<String>): String = value.joinToString(",")

    @TypeConverter
    fun toStringSet(value: String): Set<String> =
        if (value.isBlank()) emptySet() else value.split(",").toSet()

    @TypeConverter
    fun fromStringIntMap(value: Map<String, Int>): String =
        value.entries.joinToString(";") { "${it.key}:${it.value}" }

    @TypeConverter
    fun toStringIntMap(value: String): Map<String, Int> =
        if (value.isBlank()) emptyMap() else value.split(";").associate {
            val (k, v) = it.split(":")
            k to v.toInt()
        }

    @TypeConverter
    fun fromInventoryMap(value: Map<IngredientType, Int>): String =
        value.entries.joinToString(";") { "${it.key.name}:${it.value}" }

    @TypeConverter
    fun toInventoryMap(value: String): Map<IngredientType, Int> =
        if (value.isBlank()) emptyMap() else value.split(";").associate {
            val (k, v) = it.split(":")
            IngredientType.valueOf(k) to v.toInt()
        }

    @TypeConverter
    fun fromDrinksSoldMap(value: Map<MenuItemType, Long>): String =
        value.entries.joinToString(";") { "${it.key.name}:${it.value}" }

    @TypeConverter
    fun toDrinksSoldMap(value: String): Map<MenuItemType, Long> =
        if (value.isBlank()) emptyMap() else value.split(";").associate {
            val (k, v) = it.split(":")
            MenuItemType.valueOf(k) to v.toLong()
        }

    // --- Active events ---

    @TypeConverter
    fun fromActiveSeasonalEvent(value: ActiveSeasonalEvent?): String? =
        value?.let { "${it.type.name}:${it.startTimestamp}:${it.endTimestamp}:${it.eventTokensEarned}" }

    @TypeConverter
    fun toActiveSeasonalEvent(value: String?): ActiveSeasonalEvent? =
        value?.split(":")?.let { parts ->
            ActiveSeasonalEvent(
                type = SeasonalEventType.valueOf(parts[0]),
                startTimestamp = parts[1].toLong(),
                endTimestamp = parts[2].toLong(),
                eventTokensEarned = parts[3].toDouble()
            )
        }

    @TypeConverter
    fun fromActiveRandomEvents(value: List<ActiveRandomEvent>): String =
        value.joinToString("|") {
            "${it.id}:${it.type.name}:${it.startTimestamp}:${it.endTimestamp}:${it.resolved}:${it.wasHandledSuccessfully}"
        }

    @TypeConverter
    fun toActiveRandomEvents(value: String): List<ActiveRandomEvent> =
        if (value.isBlank()) emptyList() else value.split("|").map { entry ->
            val parts = entry.split(":")
            ActiveRandomEvent(
                id = parts[0],
                type = RandomEventType.valueOf(parts[1]),
                startTimestamp = parts[2].toLong(),
                endTimestamp = parts[3].toLong(),
                resolved = parts[4].toBoolean(),
                wasHandledSuccessfully = parts[5].toBoolean()
            )
        }
}
