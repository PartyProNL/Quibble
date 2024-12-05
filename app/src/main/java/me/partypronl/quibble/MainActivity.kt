package me.partypronl.quibble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.routing.QuibbleNavigationBar
import me.partypronl.quibble.routing.SetupNavGraph
import me.partypronl.quibble.routing.getFabForRoute
import me.partypronl.quibble.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseManager(this.applicationContext)

        enableEdgeToEdge()
        setContent {
            AppTheme(dynamicColor = true) {
                navController = rememberNavController()

                val currentDestination = navController
                    .currentBackStackEntryAsState()
                    .value?.destination?.route

                val currentFab: @Composable (() -> Unit)? = getFabForRoute(currentDestination, navController)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        QuibbleNavigationBar(navController = navController)
                    },
                    floatingActionButton = { currentFab?.invoke() }
                ) {
                    SetupNavGraph(navController, it)
                }
            }
        }
    }
}