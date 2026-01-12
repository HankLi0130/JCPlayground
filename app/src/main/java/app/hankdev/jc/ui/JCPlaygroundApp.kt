package app.hankdev.jc.ui

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import app.hankdev.jc.ui.screen.home.HomeScreen
import app.hankdev.jc.ui.screen.nav3.Nav3Screen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Home : Route

    @Serializable
    data object Nav3 : Route
}

@Composable
fun JCPlaygroundApp() {
    val backStack = rememberNavBackStack(Route.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Home> { HomeScreen() }
            entry<Route.Nav3> { Nav3Screen() }
        }
    )
}