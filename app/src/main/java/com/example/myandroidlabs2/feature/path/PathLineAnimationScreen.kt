package com.example.myandroidlabs2.feature.path

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathLineAnimationScreen() {
    val pathPortion = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = true) {
        pathPortion.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 5000))
    }
    val path = Path().apply {
        moveTo(100f, 100f)
        quadraticBezierTo(400f, 400f, 100f, 400f)
    }
    val outPath = Path()
    PathMeasure().apply {
        setPath(path, forceClosed = false)
        getSegment(0f, pathPortion.value * length, outPath) // outPath 에 저장되는 것
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawPath(
            path = outPath,
            color = Color.Red,
            style = Stroke(width = 5.dp.toPx())
        )
    }
}
