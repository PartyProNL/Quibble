package me.partypronl.quibble.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.partypronl.quibble.R
import me.partypronl.quibble.entry.JournalEntryUtils
import me.partypronl.quibble.util.DateUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formats a date, so that it's either "Today", "Yesterday" or formatted like "Wed 13 nov"
 * @param date The date to format
 * @return A formatted string
 */
fun formatDate(date: Long): String {
    val daysDifference = DateUtil.getDifferenceInDays(DateUtil.getToday(), date)

    val dateText = if(daysDifference == 0) {
        "Today"
    } else if(daysDifference == 1) {
        "Yesterday"
    } else {
        val dateFormat = SimpleDateFormat("EEE d MMM", Locale.UK)
        dateFormat.format(Date(date))
    }

    return dateText
}

private var fabDate = DateUtil.getToday()

@Composable
fun HomePage(innerPadding: PaddingValues) {
    var selectedDate by remember { mutableLongStateOf(DateUtil.getToday()) }

    LaunchedEffect(selectedDate) {
        fabDate = selectedDate
    }

    var loading by remember { mutableStateOf(false) }
    var entryCards by remember { mutableStateOf(listOf<@Composable () -> Unit>()) }

    LaunchedEffect(selectedDate) {
        GlobalScope.launch(Dispatchers.IO) {
            loading = true
            entryCards = JournalEntryUtils.getCardsForDate(selectedDate)
            loading = false
        }
    }

    Column(modifier = Modifier.padding(innerPadding)) {
        HomeTopBar(
            onDateSelected = { selectedDate = it },
            currentDate = selectedDate
        )

        if(loading) {
            Text("Loading...")
        } else {
            if(entryCards.isEmpty()) {
                HomeNoEntries()
            } else {
                for(card in entryCards) {
                    card()
                }
            }
        }
    }
}

@Composable
fun HomePageCreateFAB() {
    var isDialogOpen by remember { mutableStateOf(false) }

    if(isDialogOpen) {
        HomeNewEntryModal(
            fabDate,
            onDismissRequest = { isDialogOpen = false }
        )
    }

    ExtendedFloatingActionButton(
        text = { Text("New entry") },
        icon = { Icon(painterResource(R.drawable.baseline_draw_24), "Pen") },
        onClick = {
            isDialogOpen = true
        }
    )
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage(PaddingValues(0.dp))
}