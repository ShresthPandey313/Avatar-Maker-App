package com.example.bodytracking



import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.graphics.drawscope.drawImage
import androidx.compose.ui.platform.LocalContext
import kotlin.math.abs

@Composable
fun AvatarClothesOverlay() {

    val landmarks = PoseState.landmark.toList()

    if (landmarks.size < 33) {
        return
    }

    val context = LocalContext.current

    val shirtBitmap = remember(
        AvatarState.selectedShirt.intValue
    ) {
        BitmapFactory.decodeResource(
            context.resources,
            AvatarState.selectedShirt.intValue
        )
    }

    val pantBitmap = remember(
        AvatarState.selectedPant.intValue
    ) {
        BitmapFactory.decodeResource(
            context.resources,
            AvatarState.selectedPant.intValue
        )
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

//              //shirt

        val leftShoulder = landmarks[11]
        val rightShoulder = landmarks[12]

        val leftHip = landmarks[23]
        val rightHip = landmarks[24]

        val shirtCenterX =
            (
                    leftShoulder.first +
                            rightShoulder.first +
                            leftHip.first +
                            rightHip.first
                    ) / 4f * size.width

        val shirtCenterY =
            (
                    leftShoulder.second +
                            rightShoulder.second +
                            leftHip.second +
                            rightHip.second
                    ) / 4f * size.height

        val torsoWidth =
            abs(
                rightShoulder.first -
                        leftShoulder.first
            ) * size.width

        val torsoHeight =
            abs(
                leftHip.second -
                        leftShoulder.second
            ) * size.height

        val shirtWidth =
            (torsoWidth * 2.2f)
                .coerceAtLeast(150f)

        val shirtHeight =
            (torsoHeight * 2.0f)
                .coerceAtLeast(200f)

        val scaledShirt =
            android.graphics.Bitmap.createScaledBitmap(
                shirtBitmap,
                shirtWidth.toInt(),
                shirtHeight.toInt(),
                true
            )

        drawImage(
            image = scaledShirt.asImageBitmap(),
            topLeft = Offset(
                shirtCenterX - shirtWidth / 2f,
                shirtCenterY - shirtHeight / 2f
            )
        )

//                 // pannt

        val leftAnkle = landmarks[27]
        val rightAnkle = landmarks[28]

        val pantCenterX =
            (
                    leftHip.first +
                            rightHip.first +
                            leftAnkle.first +
                            rightAnkle.first
                    ) / 4f * size.width

        val pantCenterY =
            (
                    leftHip.second +
                            rightHip.second +
                            leftAnkle.second +
                            rightAnkle.second
                    ) / 4f * size.height

        val hipWidth =
            abs(
                rightHip.first -
                        leftHip.first
            ) * size.width

        val legHeight =
            abs(
                leftAnkle.second -
                        leftHip.second
            ) * size.height

        val pantWidth =
            (hipWidth * 2.0f)
                .coerceAtLeast(140f)

        val pantHeight =
            (legHeight * 1.8f)
                .coerceAtLeast(250f)

        val scaledPant =
            android.graphics.Bitmap.createScaledBitmap(
                pantBitmap,
                pantWidth.toInt(),
                pantHeight.toInt(),
                true
            )

//        drawRect(
//            color = Color.Green,
//            topLeft = Offset(
//                shirtCenterX - shirtWidth / 2f,
//                shirtCenterY - shirtHeight / 2f
//            ),
//            size = androidx.compose.ui.geometry.Size(
//                shirtWidth,
//                shirtHeight
//            )
//        )
//
//        drawRect(
//            color = Color.Blue,
//            topLeft = Offset(
//                pantCenterX - pantWidth / 2f,
//                pantCenterY - pantHeight / 2f
//            ),
//            size = androidx.compose.ui.geometry.Size(
//                pantWidth,
//                pantHeight
//            )
//        )


        drawImage(
            image = scaledPant.asImageBitmap(),
            topLeft = Offset(
                pantCenterX - pantWidth / 2f,
                pantCenterY - pantHeight / 2f
            )
        )

        Log.d(
            "SHIRT",
            """
    torsoWidth=$torsoWidth
    torsoHeight=$torsoHeight
    shirtWidth=$shirtWidth
    shirtHeight=$shirtHeight
    """.trimIndent()
        )

        Log.d(
            "PANT",
            """
    hipWidth=$hipWidth
    legHeight=$legHeight
    pantWidth=$pantWidth
    pantHeight=$pantHeight
    """.trimIndent()
        )
    }
}