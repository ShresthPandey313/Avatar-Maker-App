package com.example.bodytracking


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import kotlin.math.roundToInt

@Composable
fun AvatarImageOverlay() {

    val landmarks =
        PoseState.landmark.toList()

    if (landmarks.size < 33)
        return

    val nose =
        landmarks[0]

    val configuration =
        LocalConfiguration.current

    val screenWidth =
        configuration.screenWidthDp

    val screenHeight =
        configuration.screenHeightDp

    val leftEye = landmarks[2]
    val rightEye = landmarks[5]

    val leftEar = landmarks[7]
    val rightEar = landmarks[8]

    val headWidth =
        kotlin.math.abs(
            rightEar.first - leftEar.first
        )

    val avatarSize =
        (headWidth * screenWidth * 2.2f).dp

    val centerX =
        (leftEye.first + rightEye.first) / 2f

    val centerY =
        (leftEye.second + rightEye.second) / 2f

    Image(
        painter = painterResource(
            R.drawable.head1
        ),
        contentDescription = null,
        contentScale = ContentScale.Fit,

        modifier = Modifier
            .fillMaxSize()
            .offset {

                IntOffset(
                    (nose.first * screenWidth).roundToInt(),
                    (nose.second * screenHeight).roundToInt()
                )
            }
            .size(160.dp)
    )
}