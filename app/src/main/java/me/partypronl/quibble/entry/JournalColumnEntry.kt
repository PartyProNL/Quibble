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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.quibble.R
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.data.models.ColumnTypeModel
import me.partypronl.quibble.data.models.JournalColumnEntryModel
import me.partypronl.quibble.pages.entry.CardManageDropdownMenu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class JournalColumnEntry: JournalEntryType(
    "column",
    "Column",
    { Icon(painterResource(R.drawable.baseline_newspaper_24), "Newspaper")  },
    "write/column"
) {
    override suspend fun getCardFromJournalEntryId(journalEntryId: Long): @Composable (() -> Unit) {
        val journalColumnEntry = DatabaseManager.instance.db.journalColumnEntryDao()
            .getJournalColumnEntryByJournalEntryId(journalEntryId) ?: return {}

        val columnType = DatabaseManager.instance.db.columnTypeDao()
            .getById(journalColumnEntry.columnTypeId) ?: return {}

        return { JournalColumnEntryCard(journalColumnEntry, columnType) }
    }

    @Composable
    fun JournalColumnEntryCard(journalColumnEntry: JournalColumnEntryModel, columnType: ColumnTypeModel) {
        Box {
            OutlinedCard(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Column",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.extraLarge)
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = columnType.name,
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = journalColumnEntry.body,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            var dropdownOpen by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    dropdownOpen = true
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    tint = MaterialTheme.colorScheme.onSurface
                )

                CardManageDropdownMenu(
                    expanded = dropdownOpen,
                    onDismissRequest = { dropdownOpen = false },
                    onEdit = {},
                    onDelete = {}
                )
            }
        }
    }
}