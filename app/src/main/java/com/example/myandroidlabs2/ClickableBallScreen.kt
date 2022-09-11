package com.example.myandroidlabs2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random


@Composable
fun ClickableBallScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        var points by remember {
            mutableStateOf(0)
        }

        var isTimerRunning by remember {
            mutableStateOf(false)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Points: $points", fontSize = 20.sp)

            Button(
                onClick = {
                isTimerRunning = !isTimerRunning
                points = 0
            }) {
                Text(
                    text = if (isTimerRunning) {
                        "Running"
                    } else {
                        "Start"
                    }
                )
            }

            CountDownTimer(
                isTimerRunning = isTimerRunning
            ) {
                isTimerRunning = false
                points = 0
            }
        }
        BallClicker(enabled = isTimerRunning) {
            points++
        }
    }
}

@Composable
fun CountDownTimer(
    time: Int = 30000,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit = {}
) {
    var curTime by remember {
        mutableStateOf(time)
    }
    LaunchedEffect(key1 = curTime, key2 = isTimerRunning) {
        if (!isTimerRunning) {
            curTime = time
            return@LaunchedEffect
        }

        if (curTime > 0) {
            delay(1000L)
            curTime -= 1000
        } else {
            onTimerEnd()
        }
    }

    Text(
        text = (curTime / 1000).toString(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BallClicker(
    radius: Float = 100f,
    enabled: Boolean = false,
    ballColor: Color = Color.Red,
    onBallClick: () -> Unit = {}
) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var ballPosition by remember {
            mutableStateOf(getOffset(radius, constraints.maxWidth, constraints.maxHeight))
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(enabled) {
                    if (enabled.not()) {
                        return@pointerInput
                    }

                    detectTapGestures {
                        val dist = sqrt(
                            (ballPosition.x - it.x).pow(2) + (ballPosition.y - it.y).pow(2)
                        )

                        if (dist <= radius) {
                            onBallClick()
                            ballPosition = getOffset(
                                radius,
                                constraints.maxWidth,
                                constraints.maxHeight
                            )
                        }
                    }
                }
        ) {
            drawCircle(
                color = ballColor,
                radius = radius,
                center = ballPosition
            )
        }
    }

}

private fun getOffset(
    radius: Float,
    width: Int,
    height: Int
): Offset {
    return Offset(
        x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat(),
        y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat()
    )
}