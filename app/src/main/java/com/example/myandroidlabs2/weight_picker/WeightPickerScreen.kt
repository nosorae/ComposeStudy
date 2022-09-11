package com.example.myandroidlabs2

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.withRotation
import com.example.myandroidlabs2.weight_picker.LineType
import com.example.myandroidlabs2.weight_picker.ScaleStyle
import kotlin.math.*

@Composable
fun WeightPickerScreen() {
    var weight by remember {
        mutableStateOf(80)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f, true),
                text = weight.toString(),
                textAlign = TextAlign.Center,
                fontSize = 150.sp
            )

            Scale(
                scaleStyle = ScaleStyle(
                    scaleWidth = 150.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                weight = it
            }
        }

    }
}

@Composable
fun Scale(
    modifier: Modifier = Modifier,
    scaleStyle: ScaleStyle = ScaleStyle(),
    minWeight: Int = 20,
    maxWeight: Int = 250,
    initialWeight: Int = 80,
    onWeightChange: (Int) -> Unit
) {
    val radius = scaleStyle.radius
    val scaleWidth = scaleStyle.scaleWidth
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    var angle by remember {
        mutableStateOf(0f)
    }
    var dragStartedAngle by remember {
        mutableStateOf(0f)
    }
    var oldAngle by remember {
        mutableStateOf(angle)
    }
    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStartedAngle = -atan2(
                            y = circleCenter.x - offset.x,
                            x = circleCenter.y - offset.y
                        ) * (180f / PI.toFloat())
                        Log.d("SR-N", "onDragStart $dragStartedAngle")

                    },
                    onDragEnd = {
                        oldAngle = angle
                        Log.d("SR-N", "onDragEnd $oldAngle")

                    }
                ) { change, dragAmount ->
                    val touchAngle = -atan2(
                        y = circleCenter.x - change.position.x,
                        x = circleCenter.y - change.position.y
                    ) * (180f / PI.toFloat())
                    val newAngle = oldAngle + (touchAngle - dragStartedAngle)
                    Log.d("SR-N", "onDrag $newAngle")

                    angle = newAngle.coerceIn(
                        minimumValue = initialWeight - maxWeight.toFloat(), // todo x
                        maximumValue = initialWeight - minWeight.toFloat() // todo x
                    )
                    onWeightChange((initialWeight - angle).roundToInt())
                }
            }
    ) {
        center = this.center
        circleCenter = Offset(center.x, scaleWidth.toPx() / 2f + radius.toPx())
        val outerRadius = radius.toPx() + scaleWidth.toPx() / 2f
        val innerRadius = radius.toPx() - scaleWidth.toPx() / 2f
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    color = Color.WHITE
                    style = Paint.Style.STROKE
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        // Draw lines
        for (i in minWeight..maxWeight) {
            val angleInRad = (i - initialWeight + angle - 90) * (PI / 180f).toFloat() // 디그리로

            val lineType = when {
                i % 10 == 0 -> LineType.TenStep
                i % 5 == 0 -> LineType.FiveStep
                else -> LineType.Normal
            }

            val lineLength = when (lineType) {
                LineType.Normal -> scaleStyle.normalLineLength.toPx()
                LineType.FiveStep -> scaleStyle.fiveStepLineLength.toPx()
                LineType.TenStep -> scaleStyle.tenStepLineLength.toPx()
            }

            val lineColor = when (lineType) {
                LineType.Normal -> scaleStyle.normalLineColor
                LineType.FiveStep -> scaleStyle.fiveStepLineColor
                LineType.TenStep -> scaleStyle.tenStepLineColor
            }

            val lineStart = Offset(
                x = (outerRadius - lineLength) * cos(angleInRad) + circleCenter.x,
                y = (outerRadius - lineLength) * sin(angleInRad) + circleCenter.y
            )

            val lineEnd = Offset(
                x = outerRadius * cos(angleInRad) + circleCenter.x,
                y = outerRadius * sin(angleInRad) + circleCenter.y
            )

            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = 1.dp.toPx(),
                cap = StrokeCap.Butt
            )

            drawContext.canvas.nativeCanvas.apply {
                if (lineType is LineType.TenStep) {
                    val textRadius =
                        (outerRadius - lineLength - 5.dp.toPx() - scaleStyle.textSize.toPx())
                    val x = textRadius * cos(angleInRad) + circleCenter.x
                    val y = textRadius * sin(angleInRad) + circleCenter.y
                    withRotation(
                        degrees = angleInRad * (180f / PI.toFloat()) + 90f, // 라디언에서 다시 디그리로
                        pivotX = x,
                        pivotY = y
                    ) {
                        drawText(
                            i.toString(),
                            x,
                            y,
                            Paint().apply {
                                color = Color.BLACK
                                textAlign = Paint.Align.CENTER
                                textSize = scaleStyle.textSize.toPx()
                            }
                        )
                    }
                }
            }

            val middleTop = Offset(
                x = circleCenter.x,
                y = circleCenter.y - innerRadius - scaleStyle.scaleIndicatorLength.toPx()
            )

            val bottomLeft = Offset(
                x = circleCenter.x - 4f,
                y = circleCenter.y - innerRadius
            )

            val bottomRight = Offset(
                x = circleCenter.x + 4f,
                y = circleCenter.y - innerRadius
            )

            val indicator = Path().apply {
                moveTo(middleTop.x, middleTop.y)
                lineTo(bottomLeft.x, bottomLeft.y)
                lineTo(bottomRight.x, bottomRight.y)
                lineTo(middleTop.x, middleTop.y)
            }
            drawPath(
                path = indicator,
                color = scaleStyle.scaleIndicatorColor
            )

        }


    }
}