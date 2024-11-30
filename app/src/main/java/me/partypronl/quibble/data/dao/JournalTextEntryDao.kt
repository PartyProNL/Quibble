package me.partypronl.quibble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.partypronl.quibble.data.models.JournalTextEntryModel

@Dao
interface JournalTextEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalTextEntry: JournalTextEntryModel)

    @Query("SELECT * FROM journal_text_entry WHERE id = :id")
    suspend fun getJournalTextEntryById(id: Int): JournalTextEntryModel?

    @Query("SELECT * FROM journal_text_entry WHERE journalEntryId = :id")
    suspend fun getJournalTextEntryByJournalEntryId(id: Int): JournalTextEntryModel?
}