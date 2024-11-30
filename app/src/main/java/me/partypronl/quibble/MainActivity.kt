package me.partypronl.quibble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.partypronl.quibble.routing.QuibbleNavigationBar
import me.partypronl.quibble.routing.Route
import me.partypronl.quibble.ui.theme.AppTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import me.partypronl.quibble.data.DatabaseManager
import me.partypronl.quibble.routing.RouterView
import me.partypronl.quibble.routing.routes

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var setRoute: (route: Route) -> Unit
        lateinit var setRouteAddress: (address: String) -> Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseManager(this.applicationContext)

        enableEdgeToEdge()
        setContent {
            AppTheme(dynamicColor = true) {
                var currentRoute by remember { mutableStateOf(routes[0]) }

                setRoute = {
                    currentRoute = it
                }

                setRouteAddress = {
                    val route = routes.find { route -> route.address == it }
                    if(route != null) currentRoute = route
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        QuibbleNavigationBar(
                            routes = routes,
                            currentRouteId = currentRoute.address,
                            onSelectPage = { currentRoute = routes.find { route -> route.address == it }!! })
                    },
                    topBar = {
                        currentRoute.topBar?.invoke()
                    },
                    floatingActionButton = { currentRoute.fab?.invoke() }
                ) { innerPadding ->
                    RouterView(currentRoute, innerPadding)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme(dynamicColor = false) {
        Greeting("Android")
    }
}