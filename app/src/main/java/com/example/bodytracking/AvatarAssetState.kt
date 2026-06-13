package com.example.bodytracking

import androidx.compose.runtime.mutableIntStateOf

object AvatarAssetState {

    val selectedHead =
        mutableIntStateOf(1)

    val selectedHair =
        mutableIntStateOf(1)

    val selectedHat =
        mutableIntStateOf(1)

    val selectedShirt =
        mutableIntStateOf(1)

    val selectedPant =
        mutableIntStateOf(1)
}