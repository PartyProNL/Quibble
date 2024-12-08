package me.partypronl.quibble.routing

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    object Home: Screen("home")
    object WriteTextEntry: Screen("write/text/{date}")
    object WriteColumnEntry: Screen("write/column/{date}")
    object WriteColumnEntryBody: Screen("write/column/body/{date}/{typeId}")
    object WritePictureEntry: Screen("write/picture/{date}")
    object Search: Screen("search")
    object You: Screen("you")
}