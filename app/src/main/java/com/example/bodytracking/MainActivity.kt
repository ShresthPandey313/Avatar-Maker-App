package com.example.bodytracking

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

class MainActivity : AppCompatActivity() {

    private var hasCameraPermission = mutableStateOf(false)
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission.value = granted
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hasCameraPermission.value = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasCameraPermission.value) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        setContent {
            if (hasCameraPermission.value) {
//                Hello()
                CameraPreview()
            }
        }

    }

}

@Composable
fun CameraPreview() {

    var isFrontCamera by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        CameraPreviewContent(
            cameraPosition = isFrontCamera
        )

        Button(onClick = {isFrontCamera = !isFrontCamera},
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.BottomEnd)
                .padding(10.dp),
            shape = CircleShape
                ) {
            Icon(painter = painterResource(id = R.drawable.outline_3d_rotation_24), contentDescription = "this is ")

        }
    }



}

@Composable
fun CameraPreviewContent(cameraPosition: Boolean){


    val context = LocalContext.current
    AndroidView(
        factory = { ctx ->
            PreviewView(ctx)
        },
        update = {
            previewView ->

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                preview.setSurfaceProvider(previewView.surfaceProvider)

                val cameraSelector =
                    if (cameraPosition == true){
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    }else{
                        CameraSelector.DEFAULT_BACK_CAMERA

                    }
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    context as ComponentActivity,
                    cameraSelector,
                    preview
                )
            }, ContextCompat.getMainExecutor(context))
        }
    )


}

//@Composable
//fun Hello(){
//    Box(modifier = Modifier
//        .padding(20.dp)
//        .background(color = Color.Red)
//
//    ){
//        Text(text = "Hello World",
//            modifier = Modifier
//                .padding(5.dp),
//            color = Color.Blue,
//            fontSize = 12.sp
//        )
//
//
//    }
//}



