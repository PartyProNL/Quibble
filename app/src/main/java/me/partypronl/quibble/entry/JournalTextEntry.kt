package me.partypronl.quibble.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.quibble.R
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.data.models.JournalTextEntryModel

class JournalTextEntry: JournalEntryType(
    "text",
    "Text",
    { Icon(painterResource(R.drawable.baseline_text_snippet_24), "Text")  },
    "write/text"
) {

    override suspend fun getCardFromJournalEntryId(journalEntryId: Long): @Composable (() -> Unit) {
        val journalTextEntry = DatabaseManager.instance.db.journalTextEntryDao()
            .getJournalTextEntryByJournalEntryId(journalEntryId) ?: return {}

        return { JournalTextEntryCard(journalTextEntry) }
    }

    @Composable
    fun JournalTextEntryCard(journalTextEntry: JournalTextEntryModel) {
        Box {
            OutlinedCard(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Text entry",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.extraLarge)
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = journalTextEntry.title,
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = journalTextEntry.body,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}