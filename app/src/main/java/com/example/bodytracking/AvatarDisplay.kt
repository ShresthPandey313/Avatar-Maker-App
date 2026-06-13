package com.example.bodytracking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun AvatarOverlay() {

    val landmarks =
        PoseState.landmark.toList()

    if (landmarks.size < 29)
        return

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val nose = landmarks[0]

        val leftShoulder = landmarks[11]
        val rightShoulder = landmarks[12]

        val leftHip = landmarks[23]
        val rightHip = landmarks[24]

        val headCenter =
            Offset(
                nose.first * size.width,
                nose.second * size.height
            )

        drawCircle(
            color = Color.Yellow,
            radius = 50f,
            center = headCenter
        )

        val shoulderCenter =
            Offset(
                ((leftShoulder.first +
                        rightShoulder.first) / 2f)
                        * size.width,

                ((leftShoulder.second +
                        rightShoulder.second) / 2f)
                        * size.height
            )

        val hipCenter =
            Offset(
                ((leftHip.first +
                        rightHip.first) / 2f)
                        * size.width,

                ((leftHip.second +
                        rightHip.second) / 2f)
                        * size.height
            )

        drawLine(
            color = Color.Blue,
            start = shoulderCenter,
            end = hipCenter,
            strokeWidth = 40f
        )
    }
}