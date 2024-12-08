package me.partypronl.quibble.pages.entry.picture

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.partypronl.quibble.R
import me.partypronl.quibble.routing.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritePictureEntryTopBar(
    navController: NavController
) {
    val canSave by remember { mutableStateOf(true) }
    var saving by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Add picture entry") },
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
                },
                enabled = canSave,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text("Save")
            }
        }
    )
}