package com.example.bodytracking

//import android.graphics.Bitmap
//import androidx.camera.core.ImageProxy
//
//fun ImageProxy.toBitmap(): Bitmap? {
//    return try {
//        val buffer =
//            planes[0].buffer
//
//        val bytes =
//            ByteArray(buffer.remaining())
//
//        buffer.get(bytes)
//
//        android.graphics.BitmapFactory.decodeByteArray(
//            bytes,
//            0,
//            bytes.size
//        )
//    } catch (e: Exception) {
//        null
//    }
//}