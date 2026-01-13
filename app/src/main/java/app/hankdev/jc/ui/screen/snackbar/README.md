# Snackbar

- The basic example and Snackbar with action
  👉 [Android Developers - Snackbar](https://developer.android.com/develop/ui/compose/components/snackbar)
- Custom Snackbar
  👉 [composables](https://composables.com/docs/androidx.compose.material/material/components/Snackbar)

`Scaffold` is highly recommended because it handles the complex positioning, z-indexing, and
animations for you automatically.

```kotlin
// Create a coroutine scope to show a snackbar
val scope = rememberCoroutineScope()

// Create a snackbar host state to control the snackbar
val snackbarHostState = remember { SnackbarHostState() }

Scaffold(
    snackbarHost = {
        // Set the snackbar host
        SnackbarHost(hostState = snackbarHostState)
    },
    floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text("Show snackbar") },
            icon = { Icon(Icons.Filled.Image, contentDescription = "") },
            onClick = {
                // Show the snackbar
                scope.launch {
                    snackbarHostState.showSnackbar("Snackbar")
                }
            }
        )
    }
) { contentPadding ->
    // Screen content
}
```