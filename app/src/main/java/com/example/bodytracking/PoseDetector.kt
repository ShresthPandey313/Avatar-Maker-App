package com.example.bodytracking

import android.content.Context
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder

class PoseDetector(
    context: Context
){
    var frameCounter = 0
    var poseLandmarker : PoseLandmarker

    init {
        val baseOptions =
            BaseOptions.builder()
                .setModelAssetPath("pose_landmarker_lite.task")
                .build()

        val options =
            PoseLandmarker.PoseLandmarkerOptions
                .builder()
                .setBaseOptions(baseOptions)
                .setRunningMode(RunningMode.IMAGE)
                .build()

        poseLandmarker = PoseLandmarker
            .createFromOptions(context, options)

    }

    fun detect(bitmap: Bitmap){

        val mpImage =
            BitmapImageBuilder(bitmap)
                .build()

        val result =
            poseLandmarker.detect(mpImage)



        Log.d("pose_test", "pose detected : ${result.landmarks().size}")

    }


    fun detectLive(bitmap: Bitmap) {

        val mpImage =
            BitmapImageBuilder(bitmap)
                .build()

        val result =
            poseLandmarker.detect(mpImage)

        PoseState.landmark.clear()


        if (result.landmarks().isNotEmpty()){

            val nose = result.landmarks()[0][0]

            val leftShoulder = result.landmarks()[0][11]

            val rightShoulder = result.landmarks()[0][12]

            if(nose.visibility().orElse(0f) < 0.5f){
                return
            }

            frameCounter++

            if(frameCounter % 15 == 0){

                Log.d(
                    "POSE_DEBUG",
                    """
        Nose: ${nose.x()}, ${nose.y()}
        Left Shoulder: ${leftShoulder.x()}, ${leftShoulder.y()}
        Right Shoulder: ${rightShoulder.x()}, ${rightShoulder.y()}
        """.trimIndent()
                )

                Log.d(
                    "POSE_CONFIDENCE",
                    "x=${nose.x()} y=${nose.y()} visibility=${nose.visibility().orElse(0f)}"
                )

//                Log.d(
//                    "IMAGE_FORMAT",
//                    msg  = ImageProxy.Format
//                )
            }


            val pose = result.landmarks()[0]

            pose.forEach { landmark ->

                PoseState.landmark.add(
                    Pair(landmark.x(), landmark.y())
                )

            }

        }


    }

}

