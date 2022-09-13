package com.example.myandroidlabs2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myandroidlabs2.feature.path.PathLineAnimationScreen
import com.example.myandroidlabs2.feature.path.PathOperationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathLineAnimationScreen()
        }
    }
}