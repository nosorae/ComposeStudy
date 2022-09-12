package com.example.myandroidlabs2

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun JustDrawFeature() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp),
    ) {
        Log.d(
            "SR-N",
            "center ${center.x.toDp()} ${center.y.toDp()}"
        ) // Offset 은 스크린에서의 좌표? 캔버스의 센터 좌표
        Log.d(
            "SR-N",
            "size ${size.width.toDp()} ${size.height.toDp()}"
        ) // wid~ hei~ // 필셀 값이라서 toDp 로 찍어야 이해가 쉽다.
        drawRect(
            color = Color.Black,
            size = size,
        )

        drawRect(
            color = Color.Red,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 5.dp.toPx(),

                )
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red, Color.Yellow),
                center = center,
                radius = 100.dp.toPx()
            ),
            radius = 20f.dp.toPx(),
        )

        drawArc(
            color = Color.White,
            startAngle = 0f,
            sweepAngle = 280f,
            useCenter = false,
            topLeft = size.center,
            size = Size(200f, 200f),
            style = Stroke(
                width = 3.dp.toPx()

            )
        )

        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f, 100f),
            size = Size(200f, 300f)
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(300f, 200f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx()
        )
    }
}