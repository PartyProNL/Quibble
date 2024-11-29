package me.partypronl.quibble.entry

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.MainActivity.Companion.setRoute
import me.partypronl.quibble.R
import me.partypronl.quibble.pages.entry.text.WriteTextEntryPage
import me.partypronl.quibble.pages.entry.text.WriteTextEntryTopBar
import me.partypronl.quibble.routing.Route

class JournalTextEntry: JournalEntryType(
    "text",
    "Text",
    { Icon(painterResource(R.drawable.baseline_text_snippet_24), "Text")  }
) {
    override fun openCreatePage(date: Long) {
        val route = Route(
            "/entry/text",
            "New text entry",
            page = { WriteTextEntryPage(it) },
            topBar = { WriteTextEntryTopBar() }
        )

        setRoute(route)
    }
}