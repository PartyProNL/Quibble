package me.partypronl.quibble.pages.entry.text

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.MainActivity.Companion.setRouteAddress
import me.partypronl.quibble.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTextEntryTopBar() {
    TopAppBar(
        title = { Text("Write text entry") },
        navigationIcon = {
            IconButton(onClick = {
                setRouteAddress("/home")
            }) {
                Icon(painterResource(R.drawable.baseline_arrow_back_24), "Arrow back")
            }
        },
        actions = {
            IconButton(onClick = {
                // TODO Save and re-open home on selected date
            }) {
                Icon(painterResource(R.drawable.baseline_save_24), "Save")
            }
        }
    )
}