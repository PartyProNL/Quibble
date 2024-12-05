package me.partypronl.quibble.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.partypronl.quibble.pages.entry.text.WriteTextEntryPage
import me.partypronl.quibble.pages.home.HomePage

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable(Screen.Home.route) {
            HomePage(innerPadding)
        }

        composable(Screen.WriteTextEntry.route, listOf(
            navArgument("date") { type = NavType.LongType }
        )) {
            WriteTextEntryPage(innerPadding, it.arguments!!.getLong("date"))
        }
    }
}