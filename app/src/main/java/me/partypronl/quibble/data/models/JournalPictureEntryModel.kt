package me.partypronl.quibble.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "journal_picture_entry",
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
data class JournalPictureEntryModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val caption: String,
    val description: String,
    val journalEntryId: Long
)