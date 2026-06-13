package com.example.bodytracking

import android.R
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun TorsoOverlay() {

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        Log.d(
            "TORSO_DEBUG",
            "Landmark Count = ${PoseState.landmark.size}"
        )

        if (PoseState.landmark.size < 25) {
            return@Canvas
        }

        val leftShoulder = PoseState.landmark[11]
        val rightShoulder = PoseState.landmark[12]

        val leftHip = PoseState.landmark[23]
        val rightHip = PoseState.landmark[24]

        val topLeft =
            Offset(
                leftShoulder.first * size.width,
                leftShoulder.second * size.height
            )

        val topRight =
            Offset(
                rightShoulder.first * size.width,
                rightShoulder.second * size.height
            )

        val bottomLeft =
            Offset(
                leftHip.first * size.width,
                leftHip.second * size.height
            )

        val bottomRight =
            Offset(
                rightHip.first * size.width,
                rightHip.second * size.height
            )

        drawLine(
            color = Color.Green,
            start = topLeft,
            end = topRight,
            strokeWidth = 20f
        )

//        drawLine(
//            color = Color.Green,
//            start = topLeft,
//            end = topRight,
//            strokeWidth = 8f
//        )
//
        drawLine(
            color = Color.Green,
            start = topLeft,
            end = topRight,
            strokeWidth = 20f
        )
//
        drawLine(
            color = Color.Green,
            start = topLeft,
            end = bottomLeft,
            strokeWidth = 20f
        )
//
        drawLine(
            color = Color.Green,
            start = topRight,
            end = bottomRight,
            strokeWidth = 20f
        )
//
        drawLine(
            color = Color.Green,
            start = bottomLeft,
            end = bottomRight,
            strokeWidth = 20f
        )
    }
}


//@Composable
//fun TorsoOverlay() {
//    Canvas(
//        modifier = Modifier.fillMaxSize()
//    ) {
//
//        if (PoseState.landmark.size < 25) {
//            return@Canvas
//        }
//
//        val leftShoulder = PoseState.landmark[11]
//
//        drawCircle(
//            color = Color.Green,
//            radius = 30f,
//            center = Offset(
//                leftShoulder.first * size.width,
//                leftShoulder.second * size.height
//            )
//        )
//    }
//}