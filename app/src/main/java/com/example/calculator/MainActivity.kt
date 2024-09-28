package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                var isSplashScreenVisible by remember { mutableStateOf(true) }

                // Simulate a delay for the splash screen
                LaunchedEffect(key1 = true) {
                    delay(3000)  // Splash screen duration of 3 seconds
                    isSplashScreenVisible = false
                }

                if (isSplashScreenVisible) {
                    SplashScreen()
                } else {
                    val viewModal = viewModel<CalculatorViewModal>()
                    val state = viewModal.state
                    val buttonSpacing = 8.dp
                    Calculator(
                        state = state,
                        onAction = viewModal::onAction,
                        buttonSpacing = buttonSpacing,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    // Ensure Surface and Box are called inside a @Composable function
    androidx.compose.material3.Surface(  // Import material3 for Surface
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray  // Set the background color of the splash screen
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Display the app logo
            Image(
                painter = painterResource(id = R.drawable.calculator_icon),  // Replace with your logo resource
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp)  // Set the size to 120x120 dp
            )
        }
    }
}





