package com.example.bodytracking

data class Buttons(val icon: Int,
                   val onClick: () -> Unit)

val buttonList = listOf(

    Buttons(
        icon = R.drawable.icons8_flip_240,
        onClick = TODO(),
    )
)