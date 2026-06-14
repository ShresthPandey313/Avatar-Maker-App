package com.example.bodytracking

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AvatarCustomizer() {



    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Button(
            onClick = {
                AvatarState.selectedHead.intValue =
                    R.drawable.head1
            }
        ) {
            Text("Head 1")
        }

        Button(
            onClick = {

                AvatarState.selectedHead.intValue =
                    R.drawable.head2

                Log.d(
                    "AVATAR_TEST",
                    "Head2 clicked"
                )
            }
        ) {
            Text("Head 2")
        }

        Button(
            onClick = {
                AvatarState.selectedHead.intValue =
                    R.drawable.head3
            }
        ) {
            Text("Head 3")
        }

        Button(
            onClick = {
                AvatarState.selectedHair.intValue =
                    R.drawable.hair1
            }
        ) {
            Text("Hair 1")
        }

        Button(
            onClick = {
                AvatarState.selectedHair.intValue =
                    R.drawable.hair2
            }
        ) {
            Text("Hair 2")
        }

        Button(
            onClick = {
                AvatarState.selectedHat.intValue =
                    R.drawable.hat1
            }
        ) {
            Text("Hat 1")
        }

        Button(
            onClick = {
                AvatarState.selectedHat.intValue =
                    R.drawable.hat2
            }
        ) {
            Text("Hat 2")
        }

        Button(
            onClick = {
                AvatarState.selectedShirt.intValue =
                    R.drawable.shirt1
            }
        ) {
            Text("Shirt 1")
        }

        Button(
            onClick = {
                AvatarState.selectedShirt.intValue =
                    R.drawable.shirt2
            }
        ) {
            Text("Shirt 2")
        }

        Button(
            onClick = {
                AvatarState.selectedShirt.intValue =
                    R.drawable.shirt3
            }
        ) {
            Text("Shirt 3")
        }

        Button(
            onClick = {
                AvatarState.selectedPant.intValue =
                    R.drawable.pant1
            }
        ) {
            Text("Pant 1")
        }

        Button(
            onClick = {
                AvatarState.selectedPant.intValue =
                    R.drawable.pant2
            }
        ) {
            Text("Pant 2")
        }
    }
}