package me.partypronl.quibble.pages.entry.column

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.partypronl.quibble.R
import me.partypronl.quibble.routing.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.partypronl.quibble.components.LoadingMessage
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.data.models.ColumnTypeModel
import me.partypronl.quibble.data.models.JournalColumnEntryModel
import me.partypronl.quibble.data.models.JournalEntryModel

// This is a cheat! I need to scope this properly somehow, like in the navigation graph or something?
var viewModel: WriteColumnBodyViewModel? = null

@Composable
fun WriteColumnEntryBodyPage(
    innerPadding: PaddingValues,
    navController: NavController,
    date: Long,
    typeId: Long
) {
    if(viewModel == null) viewModel = viewModel()

    var loading by remember { mutableStateOf(false) }
    var columnType by remember { mutableStateOf<ColumnTypeModel?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel!!.setDate(date)
        viewModel!!.setTypeId(typeId)

        coroutineScope.launch(Dispatchers.IO) {
            loading = true
            columnType = DatabaseManager.instance.db.columnTypeDao().getById(typeId)
            loading = false
        }
    }

    var body by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if(loading) {
            LoadingMessage("Loading selected type...")
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = columnType?.name ?: "Type not found"
                )

                IconButton(
                    onClick = {
                        navController.navigate("write/column/$date")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text(text = columnType?.description ?: "Try again by selecting another type")

            Spacer(Modifier.height(32.dp))

            BasicTextField(
                value = body,
                onValueChange = {
                    body = it
                    viewModel!!.setBody(it)
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteColumnEntryBodyTopBar(
    navController: NavController
) {
    if(viewModel == null) viewModel = viewModel()
    val canSave by viewModel!!.canSave.collectAsState()

    var saving by remember { mutableStateOf(false) }
    var coroutineScope = rememberCoroutineScope()

    TopAppBar(
        title = { Text("Write column") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate(Screen.Home.route) }
            ) {
                Icon(painterResource(R.drawable.baseline_arrow_back_24), "Arrow back")
            }
        },
        actions = {
            TextButton(
                onClick = {
                    saving = true

                    // Save to database
                    coroutineScope.launch(Dispatchers.IO) {
                        saveColumnEntry(
                            viewModel!!.body.value,
                            viewModel!!.typeId.value,
                            viewModel!!.date.value,
                        )

                        withContext(Dispatchers.Main) {
                            navController.navigate(route = Screen.Home.route)
                        }
                    }
                },
                enabled = canSave,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text("Save")
            }
        }
    )
}

suspend fun saveColumnEntry(body: String, typeId: Long, date: Long) {
    val generatedId = DatabaseManager.instance.db.journalEntryDao().insert(JournalEntryModel(
        type = "column",
        date = date
    ))

    DatabaseManager.instance.db.journalColumnEntryDao().insert(JournalColumnEntryModel(
        body = body,
        journalEntryId = generatedId,
        columnTypeId = typeId
    ))
}