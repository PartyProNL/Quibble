package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entry")
data class JournalEntry(
    @PrimaryKey val id: Int,
    val date: Long
)