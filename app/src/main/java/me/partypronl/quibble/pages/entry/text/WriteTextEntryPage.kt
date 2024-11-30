package me.partypronl.quibble.pages.entry.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.partypronl.quibble.MainActivity.Companion.setRouteAddress
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.data.models.JournalEntryModel
import me.partypronl.quibble.data.models.JournalTextEntryModel

@Composable
fun WriteTextEntryPage(
    innerPadding: PaddingValues,
    date: Long
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    var saving by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        WriteTextEntryTopBar(body != "") {
            saving = true

            // Save to database
            GlobalScope.launch(Dispatchers.IO) {
                saveTextEntry(body, title, date)
                setRouteAddress("/home")
            }
        }

        Column(modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp)) {
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                enabled = !saving,
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = "Enter title here...",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(Modifier.height(16.dp))

            BasicTextField(
                value = body,
                onValueChange = { body = it },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                enabled = !saving,
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (body.isEmpty()) {
                        Text(
                            text = "Enter body here...",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

suspend fun saveTextEntry(body: String, title: String, date: Long) {
    val generatedId = DatabaseManager.instance.db.journalEntryDao().insert(JournalEntryModel(
        type = "text",
        date = date
    ))

    DatabaseManager.instance.db.journalTextEntryDao().insert(JournalTextEntryModel(
        title = title,
        body = body,
        journalEntryId = generatedId
    ))
}