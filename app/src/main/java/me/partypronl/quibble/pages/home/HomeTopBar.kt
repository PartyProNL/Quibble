package me.partypronl.quibble.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.quibble.R
import me.partypronl.quibble.components.DatePicker
import me.partypronl.quibble.util.DateUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onDateSelected: (Long) -> Unit, currentDate: Long) {
    var datePickerOpen by remember { mutableStateOf(false) }

    if(datePickerOpen) {
        DatePicker(
            onDismiss = { datePickerOpen = false },
            onDateSelected = { if(it != null) onDateSelected(it) },
            selectableDates = DateUtil.todayAndBeforeSelectableDates
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconButton(
                onClick = {
                    onDateSelected(DateUtil.getPreviousDay(currentDate))
                },
            ) {
                Icon(painterResource(R.drawable.baseline_chevron_left_24), "Left")
            }
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatDate(currentDate),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    datePickerOpen = true
                },
            ) {
                Icon(painterResource(R.drawable.baseline_calendar_month_24), "Calendar")
            }

            Spacer(Modifier.width(4.dp))

            val isSelectedDateToday = currentDate == DateUtil.getToday()
            IconButton(
                onClick = {
                    onDateSelected(DateUtil.getNextDay(currentDate))
                },
                enabled = !isSelectedDateToday
            ) {
                Icon(
                    painterResource(R.drawable.baseline_chevron_right_24),
                    "Right"
                )
            }
        }
    }
}