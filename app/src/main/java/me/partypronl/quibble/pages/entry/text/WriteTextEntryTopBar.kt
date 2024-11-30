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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.quibble.MainActivity.Companion.setRouteAddress
import me.partypronl.quibble.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTextEntryTopBar(canSave: Boolean, onSave: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                setRouteAddress("/home")
            }) {
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