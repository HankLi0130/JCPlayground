package app.hankdev.jc.ui.screen.snackbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.hankdev.jc.ui.common.ThemePreview
import kotlinx.coroutines.launch

private const val TAG = "SnackbarScreen"

@Composable
fun SnackbarScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                // Customize the snackbar appearance
                Snackbar(
                    actionColor = Color.Red,
                    snackbarData = data
                )
            }
        },
        floatingActionButton = {
            // To trigger the snack bar
            ExtendedFloatingActionButton(
                text = { Text("Show Snackbar") },
                icon = { Icon(imageVector = Icons.Filled.Image, contentDescription = null) },
                onClick = {
                    scope.launch {
                        // Show the snack bar
                        val result = snackbarHostState.showSnackbar(
                            message = "This is a snackbar",
                            actionLabel = "Action",
                            duration = SnackbarDuration.Indefinite
                        )

                        // Handle the snack bar result, it is optional.
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                Log.i(TAG, "ActionPerformed: ")
                            }

                            SnackbarResult.Dismissed -> {
                                Log.i(TAG, "Dismissed: ")
                            }
                        }
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("Content Here")
        }
    }
}

@Preview
@Composable
private fun SnackbarScreenPreview() {
    ThemePreview {
        SnackbarScreen()
    }
}