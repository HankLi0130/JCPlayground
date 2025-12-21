package app.hankdev.jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import app.hankdev.jc.ui.screen.TextScreen
import app.hankdev.jc.ui.theme.JCPlaygroundTheme

class MainActivity : ComponentActivity() {
    data object AScreen
    data class BScreen(val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCPlaygroundTheme {
                val backStack = remember { mutableStateListOf<Any>(AScreen) }

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = { key ->
                        when (key) {
                            is AScreen -> {
                                NavEntry(key) {
                                    TextScreen(
                                        screenLabel = "A Screen",
                                        buttonText = "Go to B",
                                        onClick = { backStack.add(BScreen("B screen")) }
                                    )
                                }
                            }

                            is BScreen -> {
                                NavEntry(key) {
                                    TextScreen(
                                        screenLabel = key.name,
                                        buttonText = "Back to A",
                                        onClick = { backStack.removeLastOrNull() }
                                    )
                                }
                            }

                            else -> error("Unknown screen: $key")
                        }
                    }
                )
            }
        }
    }
}