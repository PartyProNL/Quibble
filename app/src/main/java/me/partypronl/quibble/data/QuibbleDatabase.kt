package me.partypronl.quibble.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.partypronl.quibble.data.dao.JournalEntryDao
import me.partypronl.quibble.data.models.JournalEntry

@Database(entities = [
    JournalEntry::class
], version = 1)
abstract class QuibbleDatabase: RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
}