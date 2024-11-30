package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entry")
data class JournalEntryModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val type: String,
    val date: Long
)