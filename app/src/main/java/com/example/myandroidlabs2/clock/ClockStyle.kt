package com.example.myandroidlabs2.clock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class ClockStyle(
    val normalIndicatorColor: Color = Color.Black,
    val fiveStepIndicatorColor: Color = Color.Green,
    val normalIndicatorLength: Dp = 15.dp,
    val fiveStepIndicatorLength: Dp = 30.dp
) {
}
