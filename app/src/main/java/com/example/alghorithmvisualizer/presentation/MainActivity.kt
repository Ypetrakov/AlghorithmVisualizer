package com.example.alghorithmvisualizer.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.alghorithmvisualizer.presentation.navigation.bubbleScreen
import com.example.alghorithmvisualizer.presentation.navigation.mainScreen
import com.example.alghorithmvisualizer.presentation.ui.theme.AlghorithmVisualizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            AlghorithmVisualizerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "main_screen"
                    ) {
                        mainScreen(navController)
                        bubbleScreen()
                    }
                }
            }
        }
    }
}
