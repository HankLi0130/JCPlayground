package app.hankdev.jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.entryProvider
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
                    entryProvider = entryProvider {
                        aScreenEntry(backStack)
                        bScreenEntry(backStack)
                    }
                )
            }
        }
    }
}

private fun EntryProviderScope<Any>.aScreenEntry(backStack: SnapshotStateList<Any>) {
    entry<MainActivity.AScreen> {
        TextScreen(
            screenLabel = "A Screen",
            buttonText = "Go to B",
            onClick = { backStack.add(MainActivity.BScreen("B screen")) }
        )
    }
}

private fun EntryProviderScope<Any>.bScreenEntry(backStack: SnapshotStateList<Any>) {
    entry<MainActivity.BScreen> { key ->
        TextScreen(
            screenLabel = key.name,
            buttonText = "Back to A",
            onClick = { backStack.removeLastOrNull() }
        )
    }
}
