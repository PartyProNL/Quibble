package me.partypronl.quibble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.partypronl.quibble.data.models.JournalPictureEntryModel

@Dao
interface JournalPictureEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalPictureEntry: JournalPictureEntryModel)

    @Query("SELECT * FROM journal_picture_entry WHERE journalEntryId = :id")
    suspend fun getJournalPictureEntryByJournalEntryId(id: Long): JournalPictureEntryModel?
}