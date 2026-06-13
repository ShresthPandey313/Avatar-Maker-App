package com.example.bodytracking

import androidx.compose.runtime.mutableStateOf

object BodyMeasurement {

    val torsoHeight = mutableStateOf(0f)

    val shoulderWidth = mutableStateOf(0f)

    val bodySize = mutableStateOf("")
}