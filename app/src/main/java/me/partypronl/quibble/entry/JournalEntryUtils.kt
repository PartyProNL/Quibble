package me.partypronl.quibble.entry

import androidx.compose.runtime.Composable
import me.partypronl.quibble.data.DatabaseManager

object JournalEntryUtils {
    suspend fun getCardsForDate(date: Long): List<@Composable () -> Unit> {
        val cards = mutableListOf<@Composable () -> Unit>()

        val journalEntries = DatabaseManager.instance.db.journalEntryDao().getJournalEntriesByDate(date)
        for(journalEntry in journalEntries) {
            val type = JournalEntryType.types.find { it.id == journalEntry.type } ?: continue
            val card = type.getCardFromJournalEntryId(journalEntry.id)
            cards.add(card)
        }

        return cards
    }
}