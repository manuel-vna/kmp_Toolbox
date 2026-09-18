package com.jumparoundcreations.toolbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            GreetingText("Android")
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun AppAndroidPreview() {
    GreetingText("Android")
}
