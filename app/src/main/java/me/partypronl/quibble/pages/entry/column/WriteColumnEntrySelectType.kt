package me.partypronl.quibble.pages.entry.column

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.partypronl.quibble.R
import me.partypronl.quibble.components.LoadingMessage
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.data.models.ColumnTypeModel
import me.partypronl.quibble.routing.Screen

@Composable
fun SelectColumnTypePage(
    innerPadding: PaddingValues,
    navController: NavController,
    date: Long
) {
    var loading by remember { mutableStateOf(false) }
    var columnTypes by remember { mutableStateOf(listOf<ColumnTypeModel>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            loading = true
            columnTypes = DatabaseManager.instance.db.columnTypeDao().getAll()
            loading = false
        }
    }

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        if(loading) {
            LoadingMessage("Loading entries...")
        } else {
            if(columnTypes.isEmpty()) {
                NoColumnTypes()
            } else {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    for ((index, type) in columnTypes.withIndex()) {
                        if (index != 0) {
                            HorizontalDivider()
                        }

                        ListItem(
                            headlineContent = { Text(type.name) },
                            supportingContent = { Text(type.description) },
                            modifier = Modifier.clickable {

                            },
                            trailingContent = {
                                IconButton(onClick = {

                                }) {
                                    Icon(Icons.Default.MoreVert, "Options")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoColumnTypes() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_do_not_disturb_24),
            contentDescription = "No",
            modifier = Modifier.size(36.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "No columns found",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Create a new one with the button in the top right"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectColumnTypeTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Choose column") },
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
            IconButton(
                onClick = { }
            ) {
                Icon(painterResource(R.drawable.baseline_search_24), "Search")
            }

            IconButton(
                onClick = { }
            ) {
                Icon(painterResource(R.drawable.baseline_add_24), "Add")
            }
        }
    )
}