package me.partypronl.quibble.pages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R

@Composable
fun HomePage(innerPadding: PaddingValues) {

}

@Composable
fun HomePageCreateFAB() {
    ExtendedFloatingActionButton(
        text = { Text("New entry") },
        icon = { Icon(painterResource(R.drawable.baseline_draw_24), "Pen") },
        onClick = {}
    )
}