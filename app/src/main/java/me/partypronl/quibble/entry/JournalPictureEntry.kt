package me.partypronl.quibble.entry

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R

class JournalPictureEntry: JournalEntryType(
    "picture",
    "Picture",
    { Icon(painterResource(R.drawable.baseline_image_24), "Picture")  },
    "write/picture"
) {
    override suspend fun getCardFromJournalEntryId(journalEntryId: Long): @Composable (() -> Unit) {
        return {}
    }
}