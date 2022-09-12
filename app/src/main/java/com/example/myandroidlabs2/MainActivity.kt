package com.example.myandroidlabs2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myandroidlabs2.feature.clock.ClockScreen
import com.example.myandroidlabs2.feature.path.PathScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathScreen()
        }
    }
}