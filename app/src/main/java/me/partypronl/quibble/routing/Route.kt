package me.partypronl.quibble.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

data class Route(
    val id: String,
    val name: String,
    val icon: @Composable (() -> Unit),
    val page: @Composable ((innerPadding: PaddingValues) -> Unit)
)