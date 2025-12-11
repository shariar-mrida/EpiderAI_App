package com.epiderai.app.ui.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AnalysisScreen() {
    var isScanning by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri?.let { uri ->
                bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    }

    fun createImageUri(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(null)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", image)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Skin Analysis", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Camera Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Captured Image",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(Icons.Default.CameraAlt, contentDescription = "Camera", modifier = Modifier.size(64.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                imageUri = createImageUri()
                cameraLauncher.launch(imageUri)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Capture & Analyze")
        }

        if (bitmap != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    isScanning = true
                    // Mock delay
                    resultText = "Analysis: Mild Acne detected.\nSuggested: Salicylic Acid Cleanser."
                    isScanning = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isScanning) "Analyzing..." else "Analyze Photo")
            }
        }


        resultText?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Results", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(it)
                }
            }
        }
    }
}
