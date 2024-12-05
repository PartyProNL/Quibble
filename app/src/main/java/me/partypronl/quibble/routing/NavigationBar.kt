package me.partypronl.quibble.routing

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R

@Composable
fun QuibbleNavigationBar(navController: NavController, selectedPage: Int = 0) {
    var selectedItem by remember { mutableIntStateOf(selectedPage) }

    NavigationBar {
        NavigationBarItem(
            selected = selectedItem == 0,
            label = { Text("Home") },
            onClick = {
                selectedItem = 0
                navController.navigate(route = Screen.Home.route)
            },
            icon = { Icon(painterResource(R.drawable.baseline_menu_book_24), "Book") }
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            label = { Text("Search") },
            onClick = {
                selectedItem = 1
                navController.navigate(route = Screen.Search.route)
            },
            icon = { Icon(painterResource(R.drawable.baseline_search_24), "Search") }
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            label = { Text("You") },
            onClick = {
                selectedItem = 1
                navController.navigate(route = Screen.You.route)
            },
            icon =  { Icon(painterResource(R.drawable.baseline_person_24), "Person") }
        )
    }
}