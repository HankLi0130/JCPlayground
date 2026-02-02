package app.hankdev.jc.ui

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import app.hankdev.jc.ui.screen.basic.BasicScreen
import app.hankdev.jc.ui.screen.compositionlocal.CompositionLocalScreen
import app.hankdev.jc.ui.screen.home.HomeScreen
import app.hankdev.jc.ui.screen.nav3.Nav3Screen
import app.hankdev.jc.ui.screen.snackbar.SnackbarScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Basic : AppRoute

    @Serializable
    data object Nav3 : AppRoute

    @Serializable
    data object Snackbar : AppRoute

    @Serializable
    data object CompositionLocal : AppRoute
}

@Composable
fun JCPlaygroundApp() {
    val backStack = rememberNavBackStack(AppRoute.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<AppRoute.Home> {
                HomeScreen(onItemClick = { appRoute ->
                    when (appRoute) {
                        AppRoute.Home -> throw IllegalStateException("Home cannot be clicked")
                        AppRoute.Basic -> backStack.add(AppRoute.Basic)
                        AppRoute.Nav3 -> backStack.add(AppRoute.Nav3)
                        AppRoute.Snackbar -> backStack.add(AppRoute.Snackbar)
                        AppRoute.CompositionLocal -> backStack.add(AppRoute.CompositionLocal)
                    }
                })
            }
            entry<AppRoute.Basic> { BasicScreen() }
            entry<AppRoute.Nav3> { Nav3Screen() }
            entry<AppRoute.Snackbar> { SnackbarScreen() }
            entry<AppRoute.CompositionLocal> { CompositionLocalScreen() }
        }
    )
}