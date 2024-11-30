package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
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
    ]
)
data class JournalTextEntryModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val body: String,
    val journalEntryId: Int
)