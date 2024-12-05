package me.partypronl.quibble.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.partypronl.quibble.pages.entry.text.WriteTextEntryPage
import me.partypronl.quibble.pages.home.HomePage
import me.partypronl.quibble.pages.home.HomePageCreateFAB

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomePage(innerPadding, navController)
        }

        composable(Screen.Search.route) {
            Text("Search screen", modifier = Modifier.padding(innerPadding))
        }

        composable(Screen.You.route) {
            Text("Profile screen", modifier = Modifier.padding(innerPadding))
        }

        composable(Screen.WriteTextEntry.route, listOf(
            navArgument("date") { type = NavType.LongType }
        )) {
            WriteTextEntryPage(innerPadding, navController, it.arguments!!.getLong("date"))
        }
    }
}

@Composable
fun getFabForRoute(route: String?, navController: NavController): (@Composable () -> Unit)? {
    return when (route) {
        Screen.Home.route -> {{ HomePageCreateFAB(navController) }}
        else -> null
    }
}