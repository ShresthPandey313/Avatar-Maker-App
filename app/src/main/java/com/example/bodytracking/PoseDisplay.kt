package com.example.bodytracking

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun PoseDisplay(){

//    Canvas(modifier = Modifier.fillMaxSize()) {
//
//
//        PoseState.landmark.forEach {
//            drawCircle(
//                color = Color.Red,
//                radius = 10f,
//                center = Offset(
//                    x = it.first * size.width,
//                    it.second * size.height)
//            )
//        }
//    }


    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val landmarks = PoseState.landmark.toList()

        SkeletonConnections.forEach { connection ->
            val startIndex = connection.first
            val endIndex = connection.second

            if (startIndex < landmarks.size &&
                endIndex < landmarks.size){

                val start =
                    landmarks[startIndex]

                val end =
                    landmarks[endIndex]

                drawLine(
                    color = Color.Red,
                    start = Offset(
                        start.first * size.width,
                        start.second * size.height
                    ),
                    end = Offset(
                        end.first * size.width,
                        end.second * size.height
                ),strokeWidth = 10f
                )
            }
        }

        if (PoseState.landmark.isNotEmpty()) {



            landmarks.forEach { it ->
                drawCircle(
                    color = Color.Red,
                    radius = 10f,
                    center = Offset(
                        it.first * size.width,
                        it.second * size.height
                    )
                )
            }




        }
    }

}