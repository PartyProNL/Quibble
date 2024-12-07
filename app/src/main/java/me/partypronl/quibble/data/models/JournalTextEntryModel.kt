package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "journal_text_entry",
    foreignKeys = [
        ForeignKey(
            entity = JournalEntryModel::class,
            parentColumns = ["id"],
            childColumns = ["journalEntryId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index(value = ["journalEntryId"])]
)
data class JournalTextEntryModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val body: String,
    val journalEntryId: Long
)