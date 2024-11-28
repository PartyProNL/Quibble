package me.partypronl.quibble.routing

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun QuibbleNavigationBar(routes: List<Route>, currentRouteId: String, onSelectPage: (id: String) -> Unit) {
    NavigationBar {
        routes.forEach { route ->
            NavigationBarItem(
                selected = currentRouteId == route.id,
                label = { Text(route.name) },
                onClick = { onSelectPage(route.id) },
                icon = route.icon
            )
        }
    }
}