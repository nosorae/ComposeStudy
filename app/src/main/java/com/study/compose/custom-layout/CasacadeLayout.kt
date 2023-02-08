package com.study.compose.`custom-layout`

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

@Composable
fun CasacadeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

}

fun Modifier.custom(
    x: Int,
    y: Int
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, y)
    }
}