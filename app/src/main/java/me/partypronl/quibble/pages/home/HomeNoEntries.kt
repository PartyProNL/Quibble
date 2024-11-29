package me.partypronl.quibble.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.quibble.R

@Composable
fun HomeNoEntries() {
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
            text = "No entries",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Start writing your first for this day now"
        )
    }
}