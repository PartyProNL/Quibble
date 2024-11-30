package me.partypronl.quibble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.partypronl.quibble.data.models.JournalEntryModel

@Dao
interface JournalEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalEntry: JournalEntryModel): Long

    @Query("SELECT * FROM journal_entry WHERE id = :id")
    suspend fun getJournalEntryById(id: Long): JournalEntryModel?

    @Query("SELECT * FROM journal_entry WHERE date = :date")
    suspend fun getJournalEntriesByDate(date: Long): List<JournalEntryModel>
}