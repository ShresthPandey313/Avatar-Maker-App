package com.example.bodytracking

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.activity.compose.setContent
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private var hasCameraPermission = mutableStateOf(false)
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission.value = granted
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("DEVICE_INFO", Build.MODEL)
        Log.d("DEVICE_INFO", Build.VERSION.SDK_INT.toString())

        hasCameraPermission.value = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasCameraPermission.value) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        setContent {
            if (hasCameraPermission.value) {

                CameraPreview()
            }
        }

    }

}

@Composable
fun CameraPreview() {

    val context = LocalContext.current

    var isFrontCamera by remember { mutableStateOf(false) }
    val imageCapture = remember {
        ImageCapture.Builder().build()
    }
    Box(modifier = Modifier.fillMaxSize()){
        CameraPreviewContent(
            cameraPosition = isFrontCamera,
            imageCapture = imageCapture
        )


        var showShirt by remember { mutableStateOf(false) }
        if(showShirt){
            ShirtOverlay()
        }

        var showAvatar by remember { mutableStateOf(false) }
        if(showAvatar){
            AvatarOverlay()
        }

        var measurementBody by remember { mutableStateOf(false) }
        if(measurementBody){
            MeasurementDisplay()
        }

        var torseOverlay by remember { mutableStateOf(false) }
        if(torseOverlay){
            TorsoOverlay()
        }

        var poseDisplay by remember { mutableStateOf(false) }
        if(poseDisplay){
            PoseDisplay()
        }




        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ){
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.White.copy(alpha = 0.12f))
                    .border(
                        1.dp,
                        Color.White.copy(alpha = 0.2f),
                        RoundedCornerShape(40.dp)
                    )
            )

            val listState = rememberLazyListState(
                initialFirstVisibleItemIndex = 2
            )

            LazyRow(
                state = listState,
                modifier = Modifier
                    .matchParentSize()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                item {
                    Button(
                        onClick = {

                            if (!torseOverlay){
                                torseOverlay = true
                            }else{
                                torseOverlay = false
                            }

                        }) {
                        Text(text = "torso")
                    }

                }


                item {
                    Button(
                        onClick = {

                            if (!measurementBody){
                                measurementBody = true
                            }else{
                                measurementBody = false
                            }

                    }) {
                        Text(text = "size")
                    }

                }

                item{
                    Button( modifier = Modifier
                        .padding(10.dp),
                        shape = CircleShape,
                        onClick = {
                            if(!showShirt){
                                showShirt = true
                            }else{
                                showShirt = false
                            }
                        }
                    ) {

                        Icon(painter = painterResource(R.drawable.icons8_t_shirt_90),"dg")

                    }
                }


                item {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
//                        .fitInside(CircleShape)
                            .height(5.dp)
                            .background(Color.White)
                            .clickable

                            {

                                val contentValues = ContentValues().apply {
                                    put(MediaStore.MediaColumns.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
                                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/BodyTracking")
                                }

                                val outputOptions =
                                    ImageCapture.OutputFileOptions.Builder(
                                        context.contentResolver,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        contentValues
                                    ).build()

                                val photoFile = File(
                                    context.filesDir,
                                    "photo.jpg"
                                )

//                    val outputOptions =
//                        ImageCapture.OutputFileOptions
//                            .Builder(photoFile)
//                            .build()

                                imageCapture.takePicture(
                                    outputOptions,
                                    ContextCompat.getMainExecutor(context),
                                    object : ImageCapture.OnImageSavedCallback {

                                        override fun onImageSaved(
                                            outputFileResults: ImageCapture.OutputFileResults
                                        ) {
                                            Log.d("Camera", "Saved")
                                            Log.d("path", "${outputFileResults.savedUri}")
                                        }

                                        override fun onError(
                                            exception: ImageCaptureException
                                        ) {
                                            Log.e("Camera", "Error", exception)
                                        }
                                    }
                                )
                            }
                    ) {
//                    Icon(painter = painterResource(R.drawable.icons8_t_shirt_90),"h")

                    }
                }

                item {

                    Button(onClick = {isFrontCamera = !isFrontCamera},
                        modifier = Modifier

                            .padding(10.dp),
                        shape = CircleShape
                    ) {
                        Icon(painter = painterResource(id = R.drawable.outline_3d_rotation_24),
                            contentDescription = "this is ")

                    }
                }

                item {

                        Button(onClick = {
                            if(!showAvatar){
                                showAvatar = true
                            }else{
                                showAvatar = false
                            }
                        }) {
                            Text(text = "avatar")

                    }
                }

                item {
                    Button(
                        onClick = {

                            if (!poseDisplay){
                                poseDisplay = true
                            }else{
                                poseDisplay = false
                            }

                        }) {
                        Text(text = "torso")
                    }

                }









            }

        }

    }
}

@Composable
fun CameraPreviewContent(cameraPosition: Boolean,
                       imageCapture: ImageCapture
                              ){

    val context = LocalContext.current

    val poseDetector =
        remember { PoseDetector(context) }

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx)
        },
        update = {
            previewView ->

            val executor = Executors.newSingleThreadExecutor()

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({



                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build()

                val imageAnalysis =                  // analysing the frame
                    ImageAnalysis.Builder()
                        .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()

                imageAnalysis.setAnalyzer(executor)
                {imageProxy ->

                    val bitmap =
                        imageProxy.toBitmap()
                            ?.rotate(
                                imageProxy.imageInfo.rotationDegrees.toFloat()
                            )

                    if (bitmap != null){
                        poseDetector.detectLive(bitmap)
                    }

                    imageProxy.close()

                }

                preview.surfaceProvider = previewView.surfaceProvider



                val cameraSelector =
                    if (cameraPosition){
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    }else{
                        CameraSelector.DEFAULT_BACK_CAMERA

                    }
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    context as ComponentActivity,
                    cameraSelector,
                    preview,
                    imageAnalysis,
                    imageCapture
                )
            }, ContextCompat.getMainExecutor(context))
        }
    )


}





