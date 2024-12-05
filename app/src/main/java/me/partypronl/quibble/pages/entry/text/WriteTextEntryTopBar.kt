package me.partypronl.quibble.pages.entry.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.partypronl.quibble.R
import me.partypronl.quibble.routing.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTextEntryTopBar(canSave: Boolean, navController: NavController, onSave: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.padding(start = 8.dp),
                onClick = { navController.navigate(Screen.Home.route) }
            ) {
                Icon(painterResource(R.drawable.baseline_arrow_back_24), "Arrow back")
            }

            Text(
                text = "Write text entry",
                style = MaterialTheme.typography.titleLarge
            )
        }

        TextButton(
            onClick = { onSave() },
            enabled = canSave,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text("Save")
        }
    }
}