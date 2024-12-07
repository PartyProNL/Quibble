package me.partypronl.quibble.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.partypronl.quibble.data.dao.ColumnTypeDao
import me.partypronl.quibble.data.dao.JournalEntryDao
import me.partypronl.quibble.data.dao.JournalTextEntryDao
import me.partypronl.quibble.data.models.JournalEntryModel
import me.partypronl.quibble.data.models.JournalTextEntryModel

@Database(entities = [
    JournalEntryModel::class,
    JournalTextEntryModel::class,
    ColumnTypeDao::class
], version = 1)
abstract class QuibbleDatabase: RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
    abstract fun journalTextEntryDao(): JournalTextEntryDao
    abstract fun columnTypeDao(): ColumnTypeDao
}