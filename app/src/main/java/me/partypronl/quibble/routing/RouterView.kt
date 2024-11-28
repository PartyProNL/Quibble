package me.partypronl.quibble.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun RouterView(currentRoute: Route, innerPadding: PaddingValues) {
    currentRoute.page(innerPadding)
}