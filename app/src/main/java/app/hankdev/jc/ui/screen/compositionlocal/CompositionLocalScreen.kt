package app.hankdev.jc.ui.screen.compositionlocal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hankdev.jc.ui.common.ThemePreview

@Composable
fun CompositionLocalScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // There are two ways to create CompositionLocal: compositionLocalOf and staticCompositionLocalOf.

        // staticCompositionLocalOf
        Box(modifier = Modifier.weight(1f)) {
            StaticCompositionLocalOfContent()
        }

        // compositionLocalOf
        Box(modifier = Modifier.weight(1f)) {
            CompositionLocalOfContent()
        }
    }
}

@Preview
@Composable
private fun CompositionLocalScreenPreview() {
    ThemePreview {
        CompositionLocalScreen()
    }
}

// Create CompositionLocal instance using staticCompositionLocalOf
private val LocalString = staticCompositionLocalOf { "Default string" }

@Composable
private fun StaticCompositionLocalOfContent() {
    Column {
        Text(
            text = LocalString.current,
            color = Color.Red
        )

        // Update the composition local
        CompositionLocalProvider(LocalString provides "Changed string") {
            Text(
                text = LocalString.current,
                color = Color.Gray
            )

            // Update the composition local again
            CompositionLocalProvider(
                LocalString provides "Final string"
            ) {
                Text(
                    text = LocalString.current,
                    color = Color.Green
                )
            }
        }
    }
}

private val LocalColor = compositionLocalOf { Color.Gray }

@Composable
private fun CompositionLocalOfContent() {
    var color by remember { mutableStateOf(Color.Green) }
    CompositionLocalProvider(LocalColor provides color) {
        Box(
            modifier = Modifier
                .size(400.dp)
                .background(Color.Magenta)
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(LocalColor.current)
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Yellow)
                        .clickable {
                            color = if (color == Color.Green) Color.Gray else Color.Green
                        }
                )
            }
        }
    }
}