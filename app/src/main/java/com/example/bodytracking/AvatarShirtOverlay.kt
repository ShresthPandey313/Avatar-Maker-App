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
fun AvatarShirtOverlay() {

    val landmarks = PoseState.landmark.toList()

    if (landmarks.size < 33) {
        return
    }

    val configuration = LocalConfiguration.current

    val screenWidth =
        configuration.screenWidthDp.toFloat()

    val screenHeight =
        configuration.screenHeightDp.toFloat()

    val leftShoulder = landmarks[11]
    val rightShoulder = landmarks[12]

    val leftHip = landmarks[23]

    val centerX =
        (leftShoulder.first +
                rightShoulder.first) / 2f

    val centerY =
        (leftShoulder.second +
                leftHip.second) / 2f

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

//    val shirtWidth =
//        (torsoWidth * screenWidth * 2.5f)
//            .coerceAtLeast(120f)
    val shirtWidth =
        200f

//    val shirtHeight =
//        (torsoHeight * screenHeight * 1.5f)
//            .coerceAtLeast(150f)

    val shirtHeight =
        250f

    Image(
        painter = painterResource(
            AvatarState.selectedShirt.intValue
        ),
        contentDescription = null,
        contentScale = ContentScale.Fit,

        modifier = Modifier
            .offset {

                IntOffset(
                    (
                            centerX * screenWidth + 50
                                    - shirtWidth / 2
                            ).roundToInt(),

                    (
                            centerY * screenHeight
                                    - shirtHeight / 2
                            ).roundToInt()
                )
            }
            .size(
                shirtWidth.dp,
                shirtHeight.dp
            )
    )

    Log.d(
        "SHIRT_DEBUG",
        """
    centerX=$centerX
    centerY=$centerY
    leftShoulder=$leftShoulder
    rightShoulder=$rightShoulder
    leftHip=$leftHip
    """.trimIndent()
    )
}