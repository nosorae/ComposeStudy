package com.study.compose.canvas.path

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathOperationScreen() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val squareWithoutOp = Path().apply {
            addRect(Rect(Offset(200f, 200f), Size(200f, 200f)))
        }

        val circle = Path().apply {
            addOval(Rect(Offset(200f, 200f), 100f))
        }

        val pathWithOpDiff = Path().apply {
            op(
                path1 = circle,
                path2 = squareWithoutOp,
                PathOperation.Difference
            )
        }

        val pathWithOpUnion = Path().apply {
            op(
                path1 = circle,
                path2 = squareWithoutOp,
                PathOperation.Union // 합집합
            )
        }

        val pathWithOpXor = Path().apply {
            op(
                path1 = circle,
                path2 = squareWithoutOp,
                PathOperation.Xor // Union - Intersect
            )
        }

        val pathWithOpIntersect = Path().apply {
            op(
                path1 = circle,
                path2 = squareWithoutOp,
                PathOperation.Intersect // 교집합
            )
        }

        val pathWithOpReverseDiff = Path().apply {
            op(
                path1 = circle,
                path2 = squareWithoutOp,
                PathOperation.ReverseDifference
            )
        }

        drawPath(
            path = squareWithoutOp,
            color = Color.Red,
            style = Stroke(width = 2.dp.toPx())
        )

        drawPath(
            path = circle,
            color = Color.Blue,
            style = Stroke(width = 2.dp.toPx())
        )

        drawPath(
            path = pathWithOpDiff,
            color = Color.Green,
        )
    }
}