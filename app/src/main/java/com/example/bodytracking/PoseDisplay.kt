package com.example.bodytracking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun PoseDisplay(){

    Canvas(modifier = Modifier.fillMaxSize()) {
        PoseState.landmark.forEach {
            drawCircle(
                color = Color.Red,
                radius = 10f,
                center = Offset(
                    x = it.first * size.width,
                    it.second * size.height)
            )
        }
    }

}