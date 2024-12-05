package me.partypronl.quibble.routing

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable object Home: Screen("home")
    @Serializable object WriteTextEntry: Screen("write/text/{date}")
    @Serializable object Search: Screen("search")
    @Serializable object You: Screen("you")
}