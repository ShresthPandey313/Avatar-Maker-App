package com.example.bodytracking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue

object PoseState {

    val landmark =
        mutableStateListOf<Pair<Float, Float>>()

    var previewWidth by mutableIntStateOf(0)

    var previewHeight by mutableIntStateOf(0)
}