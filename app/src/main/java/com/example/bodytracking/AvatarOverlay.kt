package com.example.bodytracking

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.graphics.drawscope.drawImage
import androidx.compose.ui.platform.LocalContext
import kotlin.math.abs

@Composable
fun AvatarOverlays() {

    val landmarks = PoseState.landmark.toList()

    if (landmarks.size < 33) {
        return
    }

    val context = LocalContext.current

    // HEAD
    val headBitmap = remember(
        AvatarState.selectedHead.intValue
    ) {
        BitmapFactory.decodeResource(
            context.resources,
            AvatarState.selectedHead.intValue
        )
    }

    // HAIR
    val hairBitmap = remember(
        AvatarState.selectedHair.intValue
    ) {
        BitmapFactory.decodeResource(
            context.resources,
            AvatarState.selectedHair.intValue
        )
    }

    // HAT
    val hatBitmap = remember(
        AvatarState.selectedHat.intValue
    ) {
        BitmapFactory.decodeResource(
            context.resources,
            AvatarState.selectedHat.intValue
        )
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val leftEye = landmarks[2]
        val rightEye = landmarks[5]

        val leftEar = landmarks[7]
        val rightEar = landmarks[8]

        val centerX =
            (leftEye.first + rightEye.first) / 2f

        val centerY =
            (leftEye.second + rightEye.second) / 2f

        val headWidth =
            abs(
                rightEar.first -
                        leftEar.first
            ) * size.width

        val avatarSize =
            (headWidth * 5.0f)
                .coerceAtLeast(120f)

        drawAvatarBitmap(
            bitmap = headBitmap,
            centerX = centerX * size.width,
            centerY = centerY * size.height,
            sizePx = avatarSize,
            yOffset = 0f
        )

        drawAvatarBitmap(
            bitmap = hairBitmap,
            centerX = centerX * size.width,
            centerY = centerY * size.height,
            sizePx = avatarSize,
            yOffset = -avatarSize * 0.15f
        )

        drawAvatarBitmap(
            bitmap = hatBitmap,
            centerX = centerX * size.width,
            centerY = centerY * size.height,
            sizePx = avatarSize,
            yOffset = -avatarSize * 0.35f
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawAvatarBitmap(
    bitmap: Bitmap,
    centerX: Float,
    centerY: Float,
    sizePx: Float,
    yOffset: Float
) {

    val scaledBitmap =
        Bitmap.createScaledBitmap(
            bitmap,
            sizePx.toInt(),
            sizePx.toInt(),
            true
        )

    drawImage(
        image = scaledBitmap.asImageBitmap(),
        topLeft = Offset(
            centerX - sizePx / 2f,
            centerY - sizePx / 2f + yOffset
        )
    )
}