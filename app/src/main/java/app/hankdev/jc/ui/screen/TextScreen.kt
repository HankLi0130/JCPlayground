package app.hankdev.jc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hankdev.jc.ui.common.ThemePreview

@Composable
fun TextScreen(
    modifier: Modifier = Modifier,
    screenLabel: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = screenLabel)

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onClick) {
            Text(text = buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextScreenPreview() {
    ThemePreview {
        TextScreen(screenLabel = "Text Screen", buttonText = "Button Text", onClick = {})
    }
}