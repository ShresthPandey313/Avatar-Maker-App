package com.example.bodytracking



import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun ShirtOverlay() {

    val landmarks =
        PoseState.landmark.toList()

    if (landmarks.size < 25)
        return

    val leftShoulder = landmarks[11]
    val rightShoulder = landmarks[12]

    val leftHip = landmarks[23]
    val rightHip = landmarks[24]

    val centerX =
        (leftShoulder.first + rightShoulder.first) / 2f
//        leftShoulder.first

    val centerY =
        (leftShoulder.second + leftHip.second) / 2f
//        rightShoulder.second

    val shirtWidth =
        kotlin.math.abs(
            rightShoulder.first -
                    leftShoulder.first
        )

    val shirtHeight =
        kotlin.math.abs(
            leftHip.second -
                    leftShoulder.second
        )

    val torsoWidth =
        abs(
            rightShoulder.first -
                    leftShoulder.first
        )

    //

    val torsoHeight =
        abs(
            leftHip.second -
                    leftShoulder.second
        )

    Log.d(
        "TORSO_SIZE",
        "width=$torsoWidth height=$torsoHeight"
    )

    //

    val density =
        LocalDensity.current

    val widthDp =
        with(density) {
            (shirtWidth * 1000).dp
        }

    val heightDp =
        with(density) {
            (shirtHeight * 1500).dp
        }

    val shirtWidthPx = (shirtWidth * 1080)

    val shirtHeightPx = (shirtHeight * 2200)

    Image(
        painter = painterResource(
            id = R.drawable.shirt
        ),
        contentDescription = null,
        modifier = Modifier
            .offset {
                IntOffset(
                    (centerX * 1080 - shirtWidthPx - 10 ).toInt(),
                    (centerY * 2200 - shirtHeightPx ).toInt()
                )
            }
            .size(
                widthDp,
                heightDp
            )
    )
}