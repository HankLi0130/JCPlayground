package app.hankdev.jc.ui.common

import androidx.compose.runtime.Composable
import app.hankdev.jc.ui.theme.JCPlaygroundTheme

@Composable
fun ThemePreview(content: @Composable () -> Unit) {
    JCPlaygroundTheme {
        content()
    }
}