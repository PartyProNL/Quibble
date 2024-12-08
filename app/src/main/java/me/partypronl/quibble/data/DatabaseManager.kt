package me.partypronl.quibble.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import me.partypronl.quibble.data.models.ColumnTypeModel

class DatabaseManager(context: Context) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        setupDatabase(context, scope)
        instance = this
    }

    lateinit var db: QuibbleDatabase

    private fun setupDatabase(context: Context, scope: CoroutineScope) {
        db = Room.databaseBuilder(
            context.applicationContext,
            QuibbleDatabase::class.java,
            "quibble"
        )
            .fallbackToDestructiveMigration() // Only do this for local development!!! Not for production. Then you'll need to create a Migration instead
            .build()

        // Setup default column types if they don't exist
        scope.launch(Dispatchers.IO) {
            if(db.columnTypeDao().getAll().isNotEmpty()) return@launch
            setupDefaultColumnTypes()
        }
    }

    private suspend fun setupDefaultColumnTypes() {
        val defaultTypes = listOf<ColumnTypeModel>(
            ColumnTypeModel(name = "Lessons Learned",
                description = "Reflect on lessons or experiences from today that taught you something valuable"),
            ColumnTypeModel(name = "Dreams and Goals",
                description = "Write about your aspirations and how you're working towards them"),
            ColumnTypeModel(name = "Gratitude Log",
                description = "Highlight things you’re grateful for today"),
            ColumnTypeModel(name = "Mood Tracker",
                description = "Chart your emotions and analyze patterns in the long term"),
            ColumnTypeModel(name = "My Inner Dialogue",
                description = "Write about your self-talk and how it influences your mindset"),
            ColumnTypeModel(name = "Connections Count",
                description = "Reflect on meaningful interactions or relationships you developed today"),
            ColumnTypeModel(name = "Random Acts of Kindness",
                description = "Journal about kindness you’ve received or given"),
            ColumnTypeModel(name = "Role Models",
                description = "Write about people you admire and what you’ve learned from them"),
            ColumnTypeModel(name = "Passion Projects",
                description = "Document your progress in hobbies or creative pursuits"),
            ColumnTypeModel(name = "Curiosity Chronicles",
                description = "Explore topics that fascinate you and dive deep into learning about them"),
            ColumnTypeModel(name = "Current Events Through My Lens",
                description = "Reflect on how global events influence your personal life"),
            ColumnTypeModel(name = "Dream Journal",
                description = "Record your nighttime dreams or daydreams"),
            ColumnTypeModel(name = "One Year From Now",
                description = "Envision your life in the future or set a goal"),
            ColumnTypeModel(name = "Love Lessons",
                description = "Reflect on what past relationships have taught you about love and connection"),
            ColumnTypeModel(name = "Date Night Diaries",
                description = "Document memorable dates, from quirky to romantic, and how they made you feel"),
            ColumnTypeModel(name = "Crush Diaries",
                description = "Revisit past crushes and how they shaped your understanding of attraction"),
            ColumnTypeModel(name = "Love Letters Never Sent",
                description = "Write letters to people you’ve loved or admired but never expressed your feelings to"),
            ColumnTypeModel(name = "Loving Moments",
                description = "Document small, everyday gestures of love that bring you joy"),
            ColumnTypeModel(name = "Friendship and Love",
                description = "Reflect on the love and support you’ve received from friends and how it enriches your life"),
            ColumnTypeModel(name = "Family Bonds",
                description = "Explore the love you share with family members and how those relationships shape you")
        )

        for(type in defaultTypes) {
            db.columnTypeDao().insert(type)
        }
    }

    fun close() {
        scope.cancel()
    }

    companion object {
        lateinit var instance: DatabaseManager
    }
}