package me.partypronl.quibble.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

data class Route(
    val address: String,
    val name: String,
    val icon: @Composable (() -> Unit)? = null,
    val page: @Composable ((innerPadding: PaddingValues) -> Unit),
    val fab: @Composable (() -> Unit)? = null,
    val onNavigationBar: Boolean = false
)