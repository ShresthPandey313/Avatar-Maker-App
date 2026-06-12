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

        SkeletonConnections.forEach { connection ->
            val startIndex = connection.first
            val endIndex = connection.second

            if (startIndex < PoseState.landmark.size &&
                endIndex < PoseState.landmark.size){

                val startPoint = PoseState.landmark[startIndex]
                val endPoint = PoseState.landmark[endIndex]

                drawLine(
                    color = Color.Red,
                    start = Offset(
                        startPoint.first * size.width,
                        startPoint.second * size.height
                    ),
                    end = Offset(
                        endPoint.first * size.width,
                        endPoint.second * size.height
                ),strokeWidth = 10f
                )
            }
        }

        if (PoseState.landmark.isNotEmpty()) {

            PoseState.landmark.forEach { it ->
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