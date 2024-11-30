package me.partypronl.quibble.routing

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import me.partypronl.quibble.R
import me.partypronl.quibble.pages.home.HomePage
import me.partypronl.quibble.pages.home.HomePageCreateFAB

val routes = listOf(
    Route(
        address = "/home",
        name = "Home",
        icon = { Icon(painterResource(R.drawable.baseline_menu_book_24), "Book") },
        page = { innerPadding -> HomePage(innerPadding = innerPadding) },
        fab = { HomePageCreateFAB() },
        onNavigationBar = true
    ),
    Route(
        address = "/search",
        name = "Search",
        icon = { Icon(painterResource(R.drawable.baseline_search_24), "Search") },
        page = { Text("Search") },
        onNavigationBar = true
    ),
    Route(
        address = "/you",
        name = "You",
        icon = { Icon(painterResource(R.drawable.baseline_person_24), "Person") },
        page = { Text("You") },
        onNavigationBar = true
    )
)