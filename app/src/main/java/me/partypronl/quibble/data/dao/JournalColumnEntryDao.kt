package me.partypronl.quibble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.partypronl.quibble.data.models.JournalColumnEntryModel

@Dao
interface JournalColumnEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalColumnEntry: JournalColumnEntryModel)

    @Query("SELECT * FROM journal_column_entry WHERE id = :id")
    suspend fun getJournalColumnEntryById(id: Long): JournalColumnEntryModel?

    @Query("SELECT * FROM journal_column_entry WHERE journalEntryId = :id")
    suspend fun getJournalColumnEntryByJournalEntryId(id: Long): JournalColumnEntryModel?
}