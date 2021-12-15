package com.example.composemeditation_ui.helper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun Path.quadBezierFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        kotlin.math.abs(from.x + to.x) / 2f,//coordinates may be an negative values so we used absolute value
        kotlin.math.abs(from.y + to.y) / 2f
    )
}