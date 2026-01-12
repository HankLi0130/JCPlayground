package app.hankdev.jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.hankdev.jc.ui.JCPlaygroundApp
import app.hankdev.jc.ui.theme.JCPlaygroundTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCPlaygroundTheme {
                JCPlaygroundApp()
            }
        }
    }
}
