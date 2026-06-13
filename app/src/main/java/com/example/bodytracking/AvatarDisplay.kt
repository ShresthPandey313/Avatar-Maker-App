package com.example.bodytracking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun AvatarOverlay() {

    val landmarks = PoseState.landmark.toList()

    if (landmarks.size < 29) {
        return
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        // ===== HEAD =====

        val nose = landmarks[0]

        val headCenter = Offset(
            nose.first * size.width,
            nose.second * size.height
        )

        drawCircle(
            color = Color.Yellow,
            radius = 40f,
            center = headCenter
        )

        // ===== LEFT ARM =====

        val leftShoulder = Offset(
            landmarks[11].first * size.width,
            landmarks[11].second * size.height
        )

        val leftElbow = Offset(
            landmarks[13].first * size.width,
            landmarks[13].second * size.height
        )

        val leftWrist = Offset(
            landmarks[15].first * size.width,
            landmarks[15].second * size.height
        )

        drawLine(
            color = Color.Blue,
            start = leftShoulder,
            end = leftElbow,
            strokeWidth = 25f
        )

        drawLine(
            color = Color.Blue,
            start = leftElbow,
            end = leftWrist,
            strokeWidth = 25f
        )

        // ===== RIGHT ARM =====

        val rightShoulder = Offset(
            landmarks[12].first * size.width,
            landmarks[12].second * size.height
        )

        val rightElbow = Offset(
            landmarks[14].first * size.width,
            landmarks[14].second * size.height
        )

        val rightWrist = Offset(
            landmarks[16].first * size.width,
            landmarks[16].second * size.height
        )

        drawLine(
            color = Color.Blue,
            start = rightShoulder,
            end = rightElbow,
            strokeWidth = 25f
        )

        drawLine(
            color = Color.Blue,
            start = rightElbow,
            end = rightWrist,
            strokeWidth = 25f
        )

        // ===== LEFT LEG =====

        val leftHip = Offset(
            landmarks[23].first * size.width,
            landmarks[23].second * size.height
        )

        val leftKnee = Offset(
            landmarks[25].first * size.width,
            landmarks[25].second * size.height
        )

        val leftAnkle = Offset(
            landmarks[27].first * size.width,
            landmarks[27].second * size.height
        )

        drawLine(
            color = Color.DarkGray,
            start = leftHip,
            end = leftKnee,
            strokeWidth = 30f
        )

        drawLine(
            color = Color.DarkGray,
            start = leftKnee,
            end = leftAnkle,
            strokeWidth = 30f
        )

        // ===== RIGHT LEG =====

        val rightHip = Offset(
            landmarks[24].first * size.width,
            landmarks[24].second * size.height
        )

        val rightKnee = Offset(
            landmarks[26].first * size.width,
            landmarks[26].second * size.height
        )

        val rightAnkle = Offset(
            landmarks[28].first * size.width,
            landmarks[28].second * size.height
        )

        drawLine(
            color = Color.DarkGray,
            start = rightHip,
            end = rightKnee,
            strokeWidth = 30f
        )

        drawLine(
            color = Color.DarkGray,
            start = rightKnee,
            end = rightAnkle,
            strokeWidth = 30f
        )

        // ===== TORSO =====

        drawLine(
            color = Color.Green,
            start = leftShoulder,
            end = rightShoulder,
            strokeWidth = 20f
        )

        drawLine(
            color = Color.Green,
            start = leftShoulder,
            end = leftHip,
            strokeWidth = 20f
        )

        drawLine(
            color = Color.Green,
            start = rightShoulder,
            end = rightHip,
            strokeWidth = 20f
        )

        drawLine(
            color = Color.Green,
            start = leftHip,
            end = rightHip,
            strokeWidth = 20f
        )
    }
}