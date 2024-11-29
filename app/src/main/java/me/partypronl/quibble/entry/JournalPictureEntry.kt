package me.partypronl.quibble.entry

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R

class JournalPictureEntry: JournalEntryType(
    "picture",
    "Picture",
    { Icon(painterResource(R.drawable.baseline_image_24), "Picture")  }
) {
    override fun openCreatePage(date: Long) {

    }
}