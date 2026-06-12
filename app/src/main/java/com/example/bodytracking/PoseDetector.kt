package com.example.bodytracking

import android.content.Context
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import android.graphics.Bitmap
import android.util.Log
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

        if (result.landmarks().size > 0){

            val pose = result.landmarks()[0]

            pose.forEach { landmark ->

                PoseState.landmark.add(
                    Pair(landmark.x(), landmark.y())
                )

            }

        }


    }

}

