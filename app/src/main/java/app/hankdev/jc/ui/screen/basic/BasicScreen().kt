package app.hankdev.jc.ui.screen.basic

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

private const val TAG = "BasicScreen"

@Composable
fun BasicScreen() {
    // Only recompose once because it doesn't have any state.
    Log.d(TAG, "BasicScreen recomposing")

    Scaffold { paddingValues ->
        BasicScreenContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
private fun BasicScreenContent(modifier: Modifier = Modifier) {
    // Here is the state.
    var count by remember { mutableIntStateOf(0) }

    Log.d(TAG, "BasicScreenContent recomposing")

    Column(modifier = modifier) {
        Text("Count: $count")

        // It will recompose when the state changes.
        BasicComponentA(count)

        // It will not recompose.
        BasicComponentB()

        Button(
            onClick = {
                // The state changed. This will trigger a recomposition.
                count++
            }
        ) {
            Text("Increment")
        }
    }
}

@Composable
private fun BasicComponentA(value: Int) {
    Log.d(TAG, "BasicComponentA recomposing")
    Text("Component A: $value")
}

@Composable
private fun BasicComponentB() {
    Log.d(TAG, "BasicComponentB recomposing")
    Text("Component B")
}