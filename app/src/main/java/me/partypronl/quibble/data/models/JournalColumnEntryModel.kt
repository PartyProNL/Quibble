package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "journal_column_entry",
    foreignKeys = [
        ForeignKey(
            entity = JournalEntryModel::class,
            parentColumns = ["id"],
            childColumns = ["journalEntryId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = ColumnTypeModel::class,
            parentColumns = ["id"],
            childColumns = ["columnTypeId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index(value = ["journalEntryId"]), Index(value = ["columnTypeId"])]
)
data class JournalColumnEntryModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val body: String,
    val journalEntryId: Long,
    val columnTypeId: Long
)