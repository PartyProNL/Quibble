package me.partypronl.quibble.entry

import androidx.compose.runtime.Composable

abstract class JournalEntryType(
    val id: String,
    val name: String,
    val icon: @Composable () -> Unit
) {
    abstract fun openCreatePage(date: Long)

    companion object {
        val types = mutableListOf<JournalEntryType>(
            JournalTextEntry(),
            JournalColumnEntry(),
            JournalPictureEntry()
        )
    }
}