package me.partypronl.quibble.data

import android.content.Context
import androidx.room.Room

class DatabaseManager(context: Context) {
    init {
        setupDatabase(context)
        instance = this
    }

    lateinit var db: QuibbleDatabase

    private fun setupDatabase(context: Context) {
        db = Room.databaseBuilder(
            context.applicationContext,
            QuibbleDatabase::class.java,
            "quibble"
        ).build()
    }

    companion object {
        lateinit var instance: DatabaseManager
    }
}