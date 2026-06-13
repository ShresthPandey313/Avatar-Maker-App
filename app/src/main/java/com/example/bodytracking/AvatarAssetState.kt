package com.example.bodytracking

import androidx.compose.runtime.mutableIntStateOf

object AvatarState {

    val selectedHead =
        mutableIntStateOf(R.drawable.head1)

    val selectedHair =
        mutableIntStateOf(R.drawable.hair1)

    val selectedHat =
        mutableIntStateOf(R.drawable.hat1)

    val selectedShirt =
        mutableIntStateOf(R.drawable.shirt1)

    val selectedPant =
        mutableIntStateOf(R.drawable.pant1)


}