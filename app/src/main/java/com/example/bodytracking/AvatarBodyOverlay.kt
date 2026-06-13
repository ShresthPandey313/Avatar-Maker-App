package com.example.bodytracking

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.IntOffset
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun AvatarBodyOverlay() {

    val landmarks = PoseState.landmark.toList()

    if (landmarks.size < 33) return

    val configuration = LocalConfiguration.current

    val screenWidth =
        configuration.screenWidthDp.toFloat()

    val screenHeight =
        configuration.screenHeightDp.toFloat()

    val leftShoulder = landmarks[11]
    val rightShoulder = landmarks[12]

    val leftHip = landmarks[23]
    val rightHip = landmarks[24]

    val torsoCenterX =
        (leftShoulder.first + rightShoulder.first) / 2f

    val torsoCenterY =
        (leftShoulder.second + leftHip.second) / 2f

    val torsoWidth =
        abs(
            rightShoulder.first -
                    leftShoulder.first
        )

    val torsoHeight =
        abs(
            leftHip.second -
                    leftShoulder.second
        )

    val widthDp =
        (torsoWidth * screenWidth * 2f)
            .coerceAtLeast(100f)

    val heightDp =
        (torsoHeight * screenHeight * 2f)
            .coerceAtLeast(150f)

    Image(
        painter = painterResource(
            AvatarState.selectedHead.intValue
        ),
        contentDescription = null,
        contentScale = ContentScale.Fit,

        modifier = Modifier
            .offset {
                IntOffset(
                    (
                            torsoCenterX * screenWidth
                                    - widthDp / 2
                            ).roundToInt(),

                    (
                            torsoCenterY * screenHeight
                                    - heightDp / 2
                            ).roundToInt()
                )
            }
            .size(
                widthDp.dp,
                heightDp.dp
            )
    )

    Log.d(
        "AVATAR_OVERLAY",
        "Current head = ${AvatarState.selectedHead.intValue}"
    )
}