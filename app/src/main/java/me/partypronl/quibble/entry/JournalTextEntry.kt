package me.partypronl.quibble.entry

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R

class JournalTextEntry: JournalEntryType(
    "text",
    "Text",
    { Icon(painterResource(R.drawable.baseline_text_snippet_24), "Text")  }
) {
}