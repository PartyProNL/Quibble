package me.partypronl.quibble.entry

import androidx.compose.runtime.Composable

abstract class JournalEntryType(
    val id: String,
    val name: String,
    val icon: @Composable () -> Unit,
    val route: String
) {
    abstract suspend fun getCardFromJournalEntryId(journalEntryId: Long): @Composable () -> Unit

    companion object {
        val types = mutableListOf<JournalEntryType>(
            JournalTextEntry(),
            JournalColumnEntry(),
            JournalPictureEntry()
        )
    }
}