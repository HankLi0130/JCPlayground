package app.hankdev.jc.ui

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import app.hankdev.jc.ui.screen.home.HomeScreen
import app.hankdev.jc.ui.screen.nav3.Nav3Screen
import app.hankdev.jc.ui.screen.snackbar.SnackbarScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Nav3 : AppRoute

    @Serializable
    data object Snackbar : AppRoute
}

@Composable
fun JCPlaygroundApp() {
    val backStack = rememberNavBackStack(AppRoute.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<AppRoute.Home> { HomeScreen() }
            entry<AppRoute.Nav3> { Nav3Screen() }
            entry<AppRoute.Snackbar> { SnackbarScreen() }
        }
    )
}