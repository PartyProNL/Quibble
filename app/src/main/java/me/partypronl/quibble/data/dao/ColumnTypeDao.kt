package me.partypronl.quibble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.partypronl.quibble.data.models.ColumnTypeModel

@Dao
interface ColumnTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(columnType: ColumnTypeModel): Long

    @Query("SELECT * FROM column_type")
    suspend fun getAll(): List<ColumnTypeModel>
}