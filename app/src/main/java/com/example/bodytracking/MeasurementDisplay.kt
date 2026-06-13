package com.example.bodytracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MeasurementDisplay(){
    Column(modifier = Modifier
        .background(Color.Black)
        .padding(8.dp)) {
        Text(text = "shoulder : %.3f"
            .format(BodyMeasurement.shoulderWidth.value),
            color = Color.White
        )

        Text(text = "TorsoHeight : %.3f"
            .format(BodyMeasurement.torsoHeight.value),
            color = Color.Blue)

        Text(text = "BodySize : ${BodyMeasurement.bodySize.value}",
            color = Color.Gray)
    }
}