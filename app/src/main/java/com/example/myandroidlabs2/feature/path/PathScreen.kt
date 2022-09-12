package com.example.myandroidlabs2.feature.path

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun PathScreen() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(1000f, 100f) // 원하는 좌표로 붓을 이동하는 것과 같다.
            lineTo(100f, 500f) // 400 pixel 아래로
            lineTo(500f, 500f)
//            quadraticBezierTo(x1 = 800f, y1 = 300f, x2 = 500f, y2 = 100f)
            cubicTo(800f, 500f, 800f, 100f, 500f, 100f)
//            close()
        }
        drawPath(
            path,
            color = Color.Red,
            style = Stroke(
                width = 10.dp.toPx(),
                cap = StrokeCap.Butt,
                join = StrokeJoin.Miter,
                miter = 20f // cut off
            )
        )
    }
}