package com.example.weather_app_injetpackcomposeandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.weather_app_injetpackcomposeandroid.ui.theme.WeatherAppInjetpackComposeAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: WeatherViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            WeatherAppInjetpackComposeAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MyWeatherApp(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
                }
            }
        }
    }
}
