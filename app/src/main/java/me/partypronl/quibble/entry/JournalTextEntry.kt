package me.partypronl.quibble.entry

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.quibble.MainActivity.Companion.setRoute
import me.partypronl.quibble.R
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.data.models.JournalTextEntryModel
import me.partypronl.quibble.pages.entry.text.WriteTextEntryPage
import me.partypronl.quibble.pages.entry.text.WriteTextEntryTopBar
import me.partypronl.quibble.routing.Route

class JournalTextEntry: JournalEntryType(
    "text",
    "Text",
    { Icon(painterResource(R.drawable.baseline_text_snippet_24), "Text")  }
) {
    override fun openCreatePage(date: Long) {
        val route = Route(
            "/entry/text",
            "New text entry",
            page = { WriteTextEntryPage(it) },
            topBar = { WriteTextEntryTopBar() }
        )

        setRoute(route)
    }

    override suspend fun getCardFromJournalEntryId(journalEntryId: Int): @Composable (() -> Unit) {
        val journalTextEntry = DatabaseManager.instance.db.journalTextEntryDao()
            .getJournalTextEntryByJournalEntryId(journalEntryId)

        if(journalTextEntry == null) return {}
        return { JournalTextEntryCard(journalTextEntry) }
    }

    @Composable
    fun JournalTextEntryCard(journalTextEntry: JournalTextEntryModel) {
        Card {
            Text(
                text = journalTextEntry.title,
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = journalTextEntry.body,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}