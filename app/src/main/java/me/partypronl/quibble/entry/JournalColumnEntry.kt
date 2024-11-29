package me.partypronl.quibble.entry

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R

class JournalColumnEntry: JournalEntryType(
    "column",
    "Column",
    { Icon(painterResource(R.drawable.baseline_newspaper_24), "Newspaper")  }
) {
    override fun openCreatePage(date: Long) {

    }
}