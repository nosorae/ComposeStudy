package com.study.compose.canvas.clock

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ClockScreen() {
    val curTime = System.currentTimeMillis()

        Clock(initialTime = curTime, radius = 200f)
}

@Composable
fun Clock(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    initialTime: Long,
    radius: Float,
    normalIndicatorLength: Dp = 15.dp,
    fiveStepIndicatorLength: Dp = 25.dp,
    normalIndicatorColor: Color = Color.Black,
    fiveStepIndicatorColor: Color = Color.Black
) {

    val curSecond = (initialTime / 1000) % 60
    val secOffset = curSecond / 60f
    val curMin = (initialTime / 1000 / 60) % 60 + secOffset
    val minOffset = curMin / 60f
    val curHour = (initialTime / 1000 / 60 / 60) - 3f + minOffset

    var afterSec by remember {
        mutableStateOf(0)
    }

    var secAngle by remember {
        mutableStateOf(getSecAndMinHandAngle(curSecond.toFloat()))
    }

    var minAngle by remember {
        mutableStateOf(getSecAndMinHandAngle(curMin))
    }

    var hourAngle by remember {
        mutableStateOf(getHourHandAngle(curHour))
    }



    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            afterSec++

            val newTime = System.currentTimeMillis() // 이거 안 부르고 해결해보기, 앵글을 시간으로 rotate 사용하는 것이 힌트
            val newSecond = (newTime / 1000) % 60
            val newSecOffset = newSecond / 60f
            val newMin = (newTime / 1000 / 60) % 60 + newSecOffset
            val newMinOffset = newMin / 60f
            val newHour = (newTime / 1000 / 60 / 60) - 3f + newMinOffset // 로케일에 따라 조정

            secAngle = getSecAndMinHandAngle(newSecond.toFloat())
            minAngle = getSecAndMinHandAngle(newMin)
            hourAngle = getHourHandAngle(newHour)
        }
    }

    Canvas(modifier = modifier) {
        val circleCenter = Offset(
            x = center.x,
            y = center.y
        )

        drawContext.canvas.nativeCanvas.apply {

            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius,
                Paint().apply {
                    color = android.graphics.Color.WHITE
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        android.graphics.Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        for (i in 0 until 60) {
            val angle = (i * 6f) * (PI / 180f).toFloat()
            val (startRad, lineColor) = if (i % 5 == 0) {
                radius - fiveStepIndicatorLength.toPx() to fiveStepIndicatorColor
            } else {
                radius - normalIndicatorLength.toPx() to normalIndicatorColor
            }

            val startOffset = Offset(
                x = circleCenter.x + (startRad * cos(angle)),
                y = circleCenter.y + (startRad * sin(angle))
            )

            val endOffset = Offset(
                x = circleCenter.x + (radius * cos(angle)),
                y = circleCenter.y + (radius * sin(angle))
            )

            drawLine(
                color = lineColor,
                start =  startOffset,
                end = endOffset,
                strokeWidth = if (i % 5 == 0) {
                    4f
                } else {
                    2f
                }
            )
        }

        Log.d("SR-N", "Clock change $secAngle")

        drawLine(
            color = Color.Red,
            start = circleCenter,
            end = Offset(
                x = circleCenter.x + (radius - fiveStepIndicatorLength.toPx() - 2.dp.toPx()) * cos(secAngle),
                y = circleCenter.y + (radius - fiveStepIndicatorLength.toPx() - 2.dp.toPx()) * sin(secAngle)
            ),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.Black,
            start = circleCenter,
            end = Offset(
                x = circleCenter.x + (radius - fiveStepIndicatorLength.toPx() - 4.dp.toPx()) * cos(minAngle),
                y = circleCenter.y + (radius - fiveStepIndicatorLength.toPx() - 4.dp.toPx()) * sin(minAngle)
            ),
            strokeWidth = 4f
        )

        drawLine(
            color = Color.Black,
            start = circleCenter,
            end = Offset(
                x = circleCenter.x + (radius - fiveStepIndicatorLength.toPx() - 8.dp.toPx()) * cos(hourAngle),
                y = circleCenter.y + (radius - fiveStepIndicatorLength.toPx() - 8.dp.toPx()) * sin(hourAngle)
            ),
            strokeWidth = 6f
        )


    }
}

private fun getSecAndMinHandAngle(time: Float): Float {
    return (time * 6 - 90) * (PI / 180f).toFloat()
}

private fun getHourHandAngle(time: Float): Float {
    return (time * 30 - 90) * (PI / 180f).toFloat()
}