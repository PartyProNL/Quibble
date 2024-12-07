package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "column_type")
data class ColumnTypeModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val description: String,
)