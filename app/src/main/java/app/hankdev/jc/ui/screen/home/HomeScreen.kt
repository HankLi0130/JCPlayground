package app.hankdev.jc.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hankdev.jc.ui.AppRoute
import app.hankdev.jc.ui.common.ThemePreview

private data class RouteItem(val title: String, val appRoute: AppRoute)

private val allRoutes = listOf(
    RouteItem("Basic", AppRoute.Basic),
    RouteItem("Nav3", AppRoute.Nav3),
    RouteItem("Snackbar", AppRoute.Snackbar)

)

@Composable
fun HomeScreen(
    onItemClick: (AppRoute) -> Unit = {}
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = allRoutes,
                key = { it.title }
            ) { item ->
                Button(
                    onClick = { onItemClick(item.appRoute) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(item.title)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ThemePreview {
        HomeScreen()
    }
}