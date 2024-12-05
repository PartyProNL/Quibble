package me.partypronl.quibble.routing

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object WriteTextEntry: Screen("write/text/{date}")
    object Search: Screen("search")
    object You: Screen("you")
}