package com.example.bodytracking

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object PoseState {

    val landmark = mutableStateListOf<Pair<Float, Float>>()

}