package me.partypronl.quibble.pages.entry.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun WriteTextEntryPage(
    innerPadding: PaddingValues
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        WriteTextEntryTopBar(body != "") {
            title = "Saving..."
        }

        Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = MaterialTheme.typography.titleLarge,
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
                textStyle = MaterialTheme.typography.bodyLarge,
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