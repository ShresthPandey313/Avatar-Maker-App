package com.example.bodytracking

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun AvatarImageOverlay() {

    val landmarks = PoseState.landmark.toList()

    if (landmarks.size < 33) {
        return
    }

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.toFloat()
    val screenHeight = configuration.screenHeightDp.toFloat()

    // Eyes
    val leftEye = landmarks[2]
    val rightEye = landmarks[5]

    // Ears
    val leftEar = landmarks[7]
    val rightEar = landmarks[8]

    // Head center
    val centerX =
        (leftEye.first + rightEye.first) / 2f

    val centerY =
        (leftEye.second + rightEye.second) / 2f

    // Head size from ear distance
    val headWidth =
        abs(rightEar.first - leftEar.first)

    val avatarSize =
        (headWidth * screenWidth * 3.5f)
            .coerceAtLeast(120f)

    Log.d(
        "PREVIEW_STATE",
        "${PoseState.previewWidth} x ${PoseState.previewHeight}"
    )

    Log.d(
        "LANDMARK_POSITION",
        """
    nose=${landmarks[0]}
    leftShoulder=${landmarks[11]}
    rightShoulder=${landmarks[12]}
    """.trimIndent()
    )

//    Log.d(
//        "LANDMARK_COUNT",
//        PoseState.landmark.size.toString()
//    )
//
//    Log.d(
//        "NOSE",
//        "${landmarks[0].first} , ${landmarks[0].second}"
//    )

    Log.d(
        "SCREEN",
        "$screenWidth x $screenHeight"
    )

//    Image(
//        painter = painterResource(
//            id = AvatarState.selectedHead.intValue
//        ),
//        contentDescription = null,
//        contentScale = ContentScale.Fit,
//
//        modifier = Modifier
//            .offset {
//
//                IntOffset(
//                    x = (
//                            centerX * screenWidth
//                                    - avatarSize / 2f
//                            ).roundToInt(),
//
//                    y = (
//                            centerY * screenHeight
//                                    - avatarSize / 2f
//                            ).roundToInt()
//                )
//            }
//            .size(avatarSize.dp)
//    )

    Image(
        painter = painterResource(AvatarState.selectedHead.intValue),
        contentDescription = null,
        modifier = Modifier
            .offset(
                x = 100.dp,
                y = 100.dp
            )
            .size(150.dp)
    )

//    Log.d(
//        "AVATAR_OVERLAY",
//        "Current head = ${AvatarState.selectedHead.intValue}"
//    )
}